package system.rezension.studynote.service;

import system.rezension.studynote.dto.request.StudyNoteCreateRequest;
import system.rezension.studynote.dto.request.StudyNoteUpdateRequest;
import system.rezension.studynote.dto.response.StudyNoteResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface StudyNoteService {
    StudyNoteResponse createStudyNote(UserDetails userDetails,
                                      StudyNoteCreateRequest studyNoteCreateRequest);

    StudyNoteResponse readStudyNote(UserDetails userDetails,
                                    Long studyNoteId);

    List<StudyNoteResponse> readAllStudyNote(UserDetails userDetails);

    StudyNoteResponse updateStudyNote(UserDetails userDetails,
                                      StudyNoteUpdateRequest studyNoteUpdateRequest);

    StudyNoteResponse deleteStudyNote(UserDetails userDetails,
                                      Long studyNoteId);
}
