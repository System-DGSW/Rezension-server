package System.Rezension.studynote.service.impl;

import System.Rezension.studynote.dto.request.StudyNoteCreateRequest;
import System.Rezension.studynote.dto.request.StudyNoteUpdateRequest;
import System.Rezension.studynote.dto.response.StudyNoteResponse;
import System.Rezension.studynote.service.StudyNoteService;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class StudyNoteServiceImpl implements StudyNoteService {

    @Override
    public StudyNoteResponse createStudyNote(UserDetails userDetails, StudyNoteCreateRequest studyNoteCreateRequest) {
        return null;
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