package system.rezension.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system.rezension.common.web.ApiResponse;
import system.rezension.domain.member.dto.request.SignInRequest;
import system.rezension.domain.member.dto.request.SignUpRequest;
import system.rezension.domain.member.dto.response.TokenResponse;
import system.rezension.domain.member.service.MemberService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<TokenResponse> signup(@RequestBody SignUpRequest request){
        return memberService.signUp(request);
    }

    @PostMapping("/signin")
    public ApiResponse<TokenResponse> signin(@RequestBody SignInRequest request){
        return memberService.signIn(request);
    }
}
