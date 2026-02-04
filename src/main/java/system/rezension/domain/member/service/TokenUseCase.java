package system.rezension.domain.member.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import system.rezension.common.web.ApiResponse;
import system.rezension.domain.member.dto.request.GenerateTokenRequest;
import system.rezension.domain.member.dto.request.ReGenerateTokenRequest;
import system.rezension.domain.member.dto.request.SignOutRequest;
import system.rezension.domain.member.entity.Member;
import system.rezension.domain.member.exception.AuthErrorCode;
import system.rezension.domain.member.exception.AuthException;
import system.rezension.domain.member.repository.MemberRepository;
import system.rezension.global.config.RedisConfig;
import system.rezension.global.jwt.JwtProvider;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenUseCase {
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final RedisConfig redisConfig;

    public String generateAccessToken(GenerateTokenRequest request, HttpServletResponse response) {
        String accessToken = jwtProvider.createAccessToken(request.username(), request.role());
        redisConfig.redisTemplate().opsForValue().set("accessToken:" + request.username(), accessToken, 1, TimeUnit.DAYS);

        Cookie cookie = new Cookie("accessToken", accessToken);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setMaxAge(86400);
        response.addCookie(cookie);

        return accessToken;
    }

    public String generateRefreshToken(GenerateTokenRequest request, HttpServletResponse response) {
        String refreshToken = jwtProvider.createRefreshToken(request.username());
        redisConfig.redisTemplate().opsForValue().set("refreshToken:" + request.username(), refreshToken, 7, TimeUnit.DAYS);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(604800);
        response.addCookie(cookie);

        return refreshToken;
    }

    public boolean isTokenValid(String refreshToken, String username) {
        if (!jwtProvider.validateToken(refreshToken)) {
            return false;
        }

        String tokenUsername = jwtProvider.getUsernameFromToken(refreshToken);
        if (!tokenUsername.equals(username)) {
            return false;
        }

        ValueOperations<String, String> valueOperations = redisConfig.redisTemplate().opsForValue();
        String savedRefreshToken = valueOperations.get("refreshToken:" + username);
        if (savedRefreshToken != null && !savedRefreshToken.isEmpty()) {
            return refreshToken.equals(savedRefreshToken);
        }
        return false;
    }

    public ApiResponse reGenerateAccessToken(ReGenerateTokenRequest request, HttpServletResponse response) {
        String refreshToken = request.refreshToken();

        if (!jwtProvider.validateToken(refreshToken)) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }
        String username = jwtProvider.getUsernameFromToken(refreshToken);

        if (isTokenValid(refreshToken, username)) {
            Member member = memberRepository.findByUsername(username)
                    .orElseThrow(() -> new AuthException(AuthErrorCode.MEMBER_NOT_FOUND));
            GenerateTokenRequest generateTokenRequest = new GenerateTokenRequest(
                    member.getUsername(),
                    member.getRole()
            );
            String newAccessToken = generateAccessToken(generateTokenRequest, response);
            return ApiResponse.success(newAccessToken);
        }
        throw new AuthException(AuthErrorCode.INVALID_TOKEN);
    }

    public void deleteToken(SignOutRequest request, HttpServletResponse response) {
        ValueOperations<String, String> valueOperations = redisConfig.redisTemplate().opsForValue();
        String savedAccessToken = valueOperations.get("accessToken:" + request.username());
        String savedRefreshToken = valueOperations.get("refreshToken:" + request.username());
        if (savedRefreshToken == null || savedAccessToken == null) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }
        redisConfig.redisTemplate().delete("accessToken:" + request.username());
        redisConfig.redisTemplate().delete("refreshToken:" + request.username());

        Cookie accessTokenCookie = new Cookie("accessToken", null);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setHttpOnly(false);
        accessTokenCookie.setMaxAge(0);
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setMaxAge(0);
        response.addCookie(refreshTokenCookie);
    }
}

