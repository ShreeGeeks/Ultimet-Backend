package com.ultimet.user.controller;

import com.ultimet.user.base.BaseResponse;
import com.ultimet.user.service.AuthService;
import com.ultimet.user.wrapper.request.LoginForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginForm loginForm) {
        return authService.login(loginForm);
    }

    @PostMapping("/logout")
    public BaseResponse logout(@RequestParam("token") String username) {
        return authService.logout(username);
    }
}
