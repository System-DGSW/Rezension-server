package system.rezension.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode {
    // Auth 관련 에러
    INVALID_CREDENTIALS(401, "INVALID_CREDENTIALS", "이메일 또는 비밀번호가 올바르지 않습니다."),
    UNAUTHORIZED(401, "UNAUTHORIZED", "인증이 필요합니다."),
    TOKEN_EXPIRED(401, "TOKEN_EXPIRED", "토큰이 만료되었습니다."),
    INVALID_TOKEN(401, "INVALID_TOKEN", "유효하지 않은 토큰입니다."),
    FORBIDDEN(403, "FORBIDDEN", "권한이 없습니다."),

    MEMBER_NOT_FOUND(404, "MEMBER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    MEMBER_ALREADY_EXISTS(409, "MEMBER_ALREADY_EXISTS", "이미 존재하는 사용자입니다."),
    EMAIL_ALREADY_EXISTS(409, "EMAIL_ALREADY_EXISTS", "이미 사용 중인 이메일입니다."),
    EMAIL_VERIFICATION_FAILED(400, "EMAIL_VERIFICATION_FAILED", "이메일 인증에 실패하였습니다."),
    USER_ALREADY_EXISTS(409, "USER_ALREADY_EXISTS", "이미 존재하는 유저입니다."),
    USERNAME_ALREADY_EXISTS(409, "USERNAME_ALREADY_EXISTS", "중복된 이름입니다.");
    private final int status;
    private final String code;
    private final String message;
}