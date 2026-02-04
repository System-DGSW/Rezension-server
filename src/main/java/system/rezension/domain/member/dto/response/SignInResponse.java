package system.rezension.domain.member.dto.response;

public record SignInResponse(
        String accessToken,
        String refreshToken
) {
}

