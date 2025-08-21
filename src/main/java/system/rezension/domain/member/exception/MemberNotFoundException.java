package system.rezension.domain.member.exception;

import system.rezension.domain.member.exception.enums.MemberExceptionStatusCode;
import system.rezension.global.exception.StatusException;

public class MemberNotFoundException extends StatusException {
    public MemberNotFoundException() {
        super(MemberExceptionStatusCode.USER_NOT_FOUND);
    }
}
