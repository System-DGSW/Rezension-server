package system.rezension.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import system.rezension.domain.member.dto.SignInRequest;
import system.rezension.domain.member.dto.SignUpRequest;
import system.rezension.domain.member.entity.Member;
import system.rezension.domain.member.entity.Role;
import system.rezension.global.jwt.JwtProvider;
import system.rezension.domain.member.repository.MemberRepository;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> signUp(SignUpRequest request) {
        try {
            Member member = Member.builder()
                    .username(request.username())
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .role(Role.BASIC)
                    .build();
            memberRepository.save(member);
            String token = jwtProvider.createToken(request.username(), Role.BASIC);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}