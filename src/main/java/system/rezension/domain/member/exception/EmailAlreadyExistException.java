package system.rezension.domain.member.exception;

import system.rezension.domain.member.exception.enums.MemberExceptionStatusCode;
import system.rezension.global.exception.CustomStatusException;

public class EmailAlreadyExistException extends CustomStatusException {
    public EmailAlreadyExistException() {
        super(MemberExceptionStatusCode.EMAIL_ALREADY_EXIST);
    }
}
