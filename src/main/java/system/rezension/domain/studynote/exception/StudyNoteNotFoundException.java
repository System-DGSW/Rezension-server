package system.rezension.domain.studynote.exception;

import system.rezension.domain.studynote.exception.enums.StudyNoteStatusCode;
import system.rezension.global.exception.CustomStatusException;

public class StudyNoteNotFoundException extends CustomStatusException {
    public StudyNoteNotFoundException() {
        super(StudyNoteStatusCode.STUDY_NOTE_NOT_FOUND);
    }
}
