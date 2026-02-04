package system.rezension.domain.member.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {
    private final AuthErrorCode authErrorCode;

    public AuthException(AuthErrorCode authErrorCode) {
        super(authErrorCode.getMessage());
        this.authErrorCode = authErrorCode;
    }

    public AuthException(AuthErrorCode authErrorCode, String message) {
        super(message);
        this.authErrorCode = authErrorCode;
    }
}