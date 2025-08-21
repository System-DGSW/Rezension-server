package system.rezension.domain.studynote.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import system.rezension.domain.studynote.entity.StudyNote;
import system.rezension.domain.studynote.exception.StudyNoteAccessDeniedException;

@Service
public class StudyNotePermissionValidator {
    public void validate(UserDetails userDetails, StudyNote studyNote) {
        if (!studyNote.getMember().getUsername().equals(userDetails.getUsername())) {
            throw new StudyNoteAccessDeniedException();
        }
    }
}
