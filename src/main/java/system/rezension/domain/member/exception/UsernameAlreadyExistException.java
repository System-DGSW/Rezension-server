package system.rezension.domain.member.exception;

import system.rezension.domain.member.exception.enums.MemberExceptionStatusCode;
import system.rezension.global.exception.CustomStatusException;

public class UsernameAlreadyExistException extends CustomStatusException {
    public UsernameAlreadyExistException() {
        super(MemberExceptionStatusCode.USERNAME_ALREADY_EXIST);
    }
}
