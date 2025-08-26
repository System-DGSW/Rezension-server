package system.rezension.domain.member.exception;

import system.rezension.domain.member.exception.enums.MemberExceptionStatusCode;
import system.rezension.global.exception.CustomStatusException;

public class MemberNotFoundException extends CustomStatusException {
    public MemberNotFoundException() {
        super(MemberExceptionStatusCode.MEMBER_NOT_FOUND);
    }
}
