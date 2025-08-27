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
import system.rezension.domain.member.exception.EmailAlreadyExistException;
import system.rezension.domain.member.exception.LoginFailedException;
import system.rezension.domain.member.exception.MemberNotFoundException;
import system.rezension.domain.member.exception.UsernameAlreadyExistException;
import system.rezension.domain.member.repository.MemberRepository;
import system.rezension.global.jwt.JwtProvider;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> signUp(SignUpRequest request) {

        // existsByUsername 으로 바꾸는 것이 효율성 측면에서 추천됨
        if (memberRepository.findByUsername(request.username()).isPresent()) {
            throw new UsernameAlreadyExistException();
        }

        // email 중복이 검증되지 않아서 추가
        if (memberRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistException();
        }

        Member member = Member.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.BASIC)
                .build();

        memberRepository.save(member);

        String token = jwtProvider.createToken(request.username(), Role.BASIC);

        return ResponseEntity.ok(Map.of("token", token));
    }

    public ResponseEntity<?> signIn(SignInRequest request) {
        Member member = memberRepository.findByUsername(request.username())
                .orElseThrow(() -> new MemberNotFoundException());

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new LoginFailedException();
        }

        String token = jwtProvider.createToken(request.username(), Role.BASIC);
        return ResponseEntity.ok(Map.of("token", token));

    }
}