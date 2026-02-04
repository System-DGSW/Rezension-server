package system.rezension.domain.member.dto.response;


import system.rezension.domain.member.entity.Member;

public record SignUpResponse(
        String username,
        String email
) {
    public static SignUpResponse of(Member member) {
        return new SignUpResponse(
                member.getUsername(),
                member.getEmail()
        );
    }
}

