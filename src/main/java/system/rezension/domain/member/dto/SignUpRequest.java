package system.rezension.domain.member.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SignUpRequest(
        @NotNull
        String username,
        @NotNull
        @Pattern(
            regexp = PASSWORD_REGEX,
                message = "비밀번호는 최소 8글자, 대문자/소문자/특수문자를 모두 포함해야 합니다."
        )
        String password,
        @NotNull
        String email
) {
    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])" +         // 소문자 최소 1개
                    "(?=.*[A-Z])" +          // 대문자 최소 1개
                    "(?=.*\\d)" +            // 숫자 최소 1개
                    "(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/])" + // 특수문자 최소 1개
                    ".{8,}$";                // 전체 길이 최소 8
}