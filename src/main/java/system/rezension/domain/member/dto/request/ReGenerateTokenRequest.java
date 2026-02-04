package system.rezension.domain.member.dto.request;

import jakarta.validation.constraints.NotNull;

public record ReGenerateTokenRequest(
        @NotNull
        String refreshToken
) {
}

