package system.rezension.domain.studynote.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import system.rezension.domain.studynote.dto.request.StudyNoteCreateRequest;
import system.rezension.domain.studynote.dto.request.StudyNoteUpdateRequest;
import system.rezension.domain.studynote.dto.response.StudyNoteResponse;
import system.rezension.domain.studynote.entity.StudyNote;
import system.rezension.domain.studynote.error.StudyNoteNotFoundException;
import system.rezension.domain.studynote.repository.StudyNoteRepository;
import system.rezension.domain.studynote.service.StudyNoteService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyNoteServiceImpl implements StudyNoteService {

    private final StudyNoteRepository studyNoteRepository;

    // StudyNote 만들기
    @Override
    public StudyNoteResponse createStudyNote(UserDetails userDetails, StudyNoteCreateRequest studyNoteCreateRequest) {

        StudyNote studyNote = StudyNote.builder()
                .title(studyNoteCreateRequest.title())
                .content(studyNoteCreateRequest.content())
                .build();

        StudyNote savedNote = studyNoteRepository.save(studyNote);

        return StudyNoteResponse.fromStudyNoteEntity(studyNote);
    }

    // StudyNote 1개 읽기
    @Override
    public StudyNoteResponse readStudyNote(UserDetails userDetails, Long studyNoteId) {
        StudyNote studyNote = studyNoteRepository.findById(studyNoteId)
                .orElseThrow(() -> new StudyNoteNotFoundException());

        return new StudyNoteResponse(
                studyNoteId,
                studyNote.getTitle(),
                studyNote.getContent(),
                studyNote.getMember().getUsername(),
                studyNote.getCreatedAt()
        );
    }

    // StudyNote 전체 읽기
    @Override
    public List<StudyNoteResponse> readAllStudyNote(UserDetails userDetails) {
        return List.of();
    }

    @Override
    public StudyNoteResponse updateStudyNote(UserDetails userDetails, StudyNoteUpdateRequest studyNoteUpdateRequest) {
        return null;
    }

    @Override
    public StudyNoteResponse deleteStudyNote(UserDetails userDetails, Long studyNoteId) {
        return null;
    }
}