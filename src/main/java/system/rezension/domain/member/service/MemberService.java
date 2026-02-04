package system.rezension.domain.member.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import system.rezension.common.web.ApiResponse;
import system.rezension.domain.member.dto.request.GenerateTokenRequest;
import system.rezension.domain.member.dto.request.SignInRequest;
import system.rezension.domain.member.dto.request.SignUpRequest;
import system.rezension.domain.member.dto.response.SignInResponse;
import system.rezension.domain.member.entity.Member;
import system.rezension.domain.member.exception.AuthErrorCode;
import system.rezension.domain.member.exception.AuthException;
import system.rezension.domain.member.repository.MemberRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenUseCase tokenUseCase;

    public ApiResponse signUp(SignUpRequest request) {
        if (memberRepository.existsByUsername(request.username())) {
            throw new AuthException(AuthErrorCode.MEMBER_ALREADY_EXISTS);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = LocalDateTime.now().format(formatter);

        Member member = Member.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .role(request.role())
                .build();
        memberRepository.save(member);

        return ApiResponse.success("회원가입에 성공하셨습니다.");

    }


    public ApiResponse<SignInResponse> signIn(SignInRequest request, HttpServletResponse response) {
        Member member = memberRepository.findByUsername(request.username())
                .orElseThrow(() -> new AuthException(AuthErrorCode.MEMBER_NOT_FOUND));
        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new AuthException(AuthErrorCode.INVALID_CREDENTIALS);
        }

        GenerateTokenRequest generateTokenRequest = new GenerateTokenRequest(
                member.getUsername(),
                member.getRole()
        );

        String accessToken = tokenUseCase.generateAccessToken(generateTokenRequest, response);
        String refreshToken = tokenUseCase.generateRefreshToken(generateTokenRequest, response);

        return ApiResponse.success(new SignInResponse(accessToken,refreshToken));
    }
}

