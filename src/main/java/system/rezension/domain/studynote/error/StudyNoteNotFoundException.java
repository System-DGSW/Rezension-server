package system.rezension.domain.studynote.error;

import system.rezension.domain.studynote.error.enums.StudyNoteStatusCode;
import system.rezension.global.exception.StatusException;

public class StudyNoteNotFoundException extends StatusException {
    public StudyNoteNotFoundException() {
        super(StudyNoteStatusCode.STUDY_NOTE_NOT_FOUND);
    }
}
