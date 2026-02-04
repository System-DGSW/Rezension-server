package system.rezension.domain.member.dto.request;


import system.rezension.domain.member.entity.Role;

public record GenerateTokenRequest(
        String username,
        Role role
) {
}

