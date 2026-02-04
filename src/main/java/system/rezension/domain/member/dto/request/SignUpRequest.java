package system.rezension.domain.member.dto.request;

import jakarta.validation.constraints.NotNull;
import system.rezension.domain.member.entity.Role;

public record SignUpRequest(
        @NotNull
        String username,
        @NotNull
        String password,
        @NotNull
        String email,
        Role role
)
{ }

