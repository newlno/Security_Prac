package com.example.Security_Prac.controller;

import com.example.Security_Prac.dto.request.UserRequestDto;
import com.example.Security_Prac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * @return 회원가입 페이지 리소스
     */
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(
            @RequestBody UserRequestDto requestDto
    ) {
        userService.signup(requestDto);
        // 회원가입 후 로그인 페이지로 이동
        return "redirect:login";
    }
}
