package system.rezension.domain.studynote.error.enums;

import lombok.AllArgsConstructor;
import system.rezension.global.exception.enums.StatusCode;

@AllArgsConstructor
public enum StudyNoteStatusCode implements StatusCode {
    STUDY_NOTE_NOT_FOUND(404, "스터디 노트를 찾을 수 없음");

    private final int status;

    private final String message;

    @Override
    public int getStatusCode() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getExceptionName() {
        return this.name();
    }
}
