package system.rezension.domain.studynote.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import system.rezension.domain.studynote.dto.request.StudyNoteCreateRequest;
import system.rezension.domain.studynote.dto.request.StudyNoteUpdateRequest;
import system.rezension.domain.studynote.dto.response.StudyNoteResponse;

import java.util.List;

public interface StudyNoteService {
    StudyNoteResponse createStudyNote(UserDetails userDetails,
                                      StudyNoteCreateRequest studyNoteCreateRequest);

    StudyNoteResponse readStudyNote(UserDetails userDetails,
                                    Long studyNoteId);

    Page<StudyNoteResponse> readStudyNotePage(UserDetails userDetails, Long memberId, Pageable pageable);

    List<StudyNoteResponse> readAllStudyNote(UserDetails userDetails);

    StudyNoteResponse updateStudyNote(UserDetails userDetails,
                                      StudyNoteUpdateRequest studyNoteUpdateRequest);

    StudyNoteResponse deleteStudyNote(UserDetails userDetails,
                                      Long studyNoteId);
}
