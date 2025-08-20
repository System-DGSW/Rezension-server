package System.Rezension.studynote.service;

import System.Rezension.studynote.dto.request.StudyNoteCreateRequest;
import System.Rezension.studynote.dto.request.StudyNoteUpdateRequest;
import System.Rezension.studynote.dto.response.StudyNoteResponse;
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
