package system.rezension.domain.studynote.exception;

import system.rezension.domain.studynote.exception.enums.StudyNoteStatusCode;
import system.rezension.global.exception.CustomStatusException;

public class StudyNoteAccessDeniedException extends CustomStatusException {
    public StudyNoteAccessDeniedException() {
        super(StudyNoteStatusCode.STUDY_NOTE_ACCESS_DENIED);
    }
}
