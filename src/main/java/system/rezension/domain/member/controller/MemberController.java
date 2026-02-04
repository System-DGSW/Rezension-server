package system.rezension.domain.member.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system.rezension.common.web.ApiResponse;
import system.rezension.domain.member.dto.request.SignInRequest;
import system.rezension.domain.member.dto.request.SignUpRequest;
import system.rezension.domain.member.service.MemberService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return memberService.signUp(signUpRequest);
    }

    @PostMapping("/signin")
    public ApiResponse<?> signIn(@Valid @RequestBody SignInRequest signInRequest, HttpServletResponse response) {
        return memberService.signIn(signInRequest, response);
    }
}