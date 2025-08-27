package system.rezension.domain.member.exception;

import system.rezension.domain.member.exception.enums.MemberExceptionStatusCode;
import system.rezension.global.exception.CustomStatusException;

public class LoginFailedException extends CustomStatusException {
    public LoginFailedException() {
        super(MemberExceptionStatusCode.LOGIN_FAIL);
    }
}
