package System.Rezension.studynote.service.impl;

import System.Rezension.studynote.dto.request.StudyNoteCreateRequest;
import System.Rezension.studynote.dto.request.StudyNoteUpdateRequest;
import System.Rezension.studynote.dto.response.StudyNoteResponse;
import System.Rezension.studynote.entity.StudyNote;
import System.Rezension.studynote.repository.StudyNoteRepository;
import System.Rezension.studynote.service.StudyNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyNoteServiceImpl implements StudyNoteService {

    private final StudyNoteRepository studyNoteRepository;

    @Override
    public StudyNoteResponse createStudyNote(UserDetails userDetails, StudyNoteCreateRequest studyNoteCreateRequest) {

        StudyNote studyNote = StudyNote.builder()
                .title(studyNoteCreateRequest.title())
                .content(studyNoteCreateRequest.content())
                .build();


        StudyNote savedNote = studyNoteRepository.save(studyNote);


        return new StudyNoteResponse(
                savedNote.getId(),
                savedNote.getTitle(),
                savedNote.getContent(),
                savedNote.getMember().getUsername(),
                savedNote.getCreatedAt()
        );
    }

    @Override
    public StudyNoteResponse readStudyNote(UserDetails userDetails, Long studyNoteId) {
        return null;
    }

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