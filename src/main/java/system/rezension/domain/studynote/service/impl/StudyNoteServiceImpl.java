package system.rezension.domain.studynote.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.rezension.domain.member.entity.Member;
import system.rezension.domain.member.exception.MemberNotFoundException;
import system.rezension.domain.member.repository.MemberRepository;
import system.rezension.domain.studynote.dto.request.StudyNoteCreateRequest;
import system.rezension.domain.studynote.dto.request.StudyNoteUpdateRequest;
import system.rezension.domain.studynote.dto.response.StudyNoteResponse;
import system.rezension.domain.studynote.entity.StudyNote;
import system.rezension.domain.studynote.exception.StudyNoteNotFoundException;
import system.rezension.domain.studynote.repository.StudyNoteRepository;
import system.rezension.domain.studynote.service.StudyNotePermissionValidator;
import system.rezension.domain.studynote.service.StudyNoteService;

import java.util.List;

import static system.rezension.domain.studynote.entity.Visibility.PUBLIC;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyNoteServiceImpl implements StudyNoteService {

    private final StudyNoteRepository studyNoteRepository;
    private final MemberRepository memberRepository;
    // validate 메서드가 StudyNote의 멤버와 현재 로그인 한 멤버와 같은지 확인합니다.
    private final StudyNotePermissionValidator studyNoteValidator;

    // StudyNote 만들기
    @Override
    public StudyNoteResponse createStudyNote(UserDetails userDetails, StudyNoteCreateRequest studyNoteCreateRequest) {

        Member member = memberRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(MemberNotFoundException::new);

        StudyNote studyNote = StudyNote.builder()
                .title(studyNoteCreateRequest.title())
                .content(studyNoteCreateRequest.content())
                .member(member)
                .visibility(studyNoteCreateRequest.visibility())
                .build();

        StudyNote savedNote = studyNoteRepository.save(studyNote);

        return StudyNoteResponse.fromStudyNoteEntity(savedNote);
    }

    // StudyNote 1개 읽기
    @Override
    public StudyNoteResponse readStudyNote(UserDetails userDetails, Long studyNoteId) {
        StudyNote studyNote = studyNoteRepository.findById(studyNoteId)
                .orElseThrow(StudyNoteNotFoundException::new);

        studyNoteValidator.validate(userDetails, studyNote);

        return StudyNoteResponse.fromStudyNoteEntity(studyNote);
    }

    // Page 이용해 StudyNote 읽기
    @Override
    public Page<StudyNoteResponse> readStudyNotePage(UserDetails userDetails, Long memberId, Pageable pageable) {
        Member requester = memberRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(MemberNotFoundException::new);

        Page<StudyNote> studyNotes;
        if (requester.getId().equals(memberId)) {
            studyNotes = studyNoteRepository.findByMemberId(memberId, pageable);
        } else {
            studyNotes = studyNoteRepository.findByMemberIdAndVisibility(memberId, PUBLIC, pageable);
        }

        return studyNotes.map(StudyNoteResponse::fromStudyNoteEntity);
    }

    // StudyNote 전체 읽기
    @Override
    public List<StudyNoteResponse> readAllStudyNote(UserDetails userDetails) {
        Member member = memberRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(MemberNotFoundException::new);

        List<StudyNote> allStudyNotes = studyNoteRepository.findAllByMember(member);
        return allStudyNotes.stream()
                .map(StudyNoteResponse::fromStudyNoteEntity)
                .toList();
    }

    @Override
    public StudyNoteResponse updateStudyNote(UserDetails userDetails, StudyNoteUpdateRequest studyNoteUpdateRequest, Long studyNoteId) {
        StudyNote studyNote = studyNoteRepository.findById(studyNoteId)
                .orElseThrow(StudyNoteNotFoundException::new);

        studyNoteValidator.validate(userDetails, studyNote);

        studyNoteUpdateRequest.title().ifPresent(studyNote::setTitle);
        studyNoteUpdateRequest.content().ifPresent(studyNote::setContent);
        studyNoteUpdateRequest.visibility().ifPresent(studyNote::setVisibility);
        StudyNote saved = studyNoteRepository.save(studyNote);
        return StudyNoteResponse.fromStudyNoteEntity(saved);
    }

    @Override
    public StudyNoteResponse deleteStudyNote(UserDetails userDetails, Long studyNoteId) {
        StudyNote studyNote = studyNoteRepository.findById(studyNoteId)
                .orElseThrow(StudyNoteNotFoundException::new);

        studyNoteValidator.validate(userDetails, studyNote);

        studyNoteRepository.delete(studyNote);
        return StudyNoteResponse.fromStudyNoteEntity(studyNote);
    }
}