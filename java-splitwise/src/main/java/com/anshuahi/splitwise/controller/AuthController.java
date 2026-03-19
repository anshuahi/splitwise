package com.anshuahi.splitwise.controller;

import com.anshuahi.splitwise.dto.AuthResponse;
import com.anshuahi.splitwise.dto.LoginRequest;
import com.anshuahi.splitwise.dto.SignupRequest;
import com.anshuahi.splitwise.service.AuthService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@NoArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        System.out.println(request);
        return ResponseEntity.ok(authService.loginUser(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request){
        System.out.println(request);
        return ResponseEntity.ok(authService.signup(request));
    }
}
