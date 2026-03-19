package com.anshuahi.splitwise.service;

import com.anshuahi.splitwise.dto.AuthResponse;
import com.anshuahi.splitwise.dto.LoginRequest;
import com.anshuahi.splitwise.dto.SignupRequest;
import com.anshuahi.splitwise.dto.UserDto;
import com.anshuahi.splitwise.model.User;
import com.anshuahi.splitwise.repository.UserRepository;
import com.anshuahi.splitwise.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse signup(SignupRequest request){
        userRepository.findByPhone(request.getPhone())
                .ifPresent(u -> {
                    throw new RuntimeException("Phone already exists");
                });
        User user = User.builder()
                .phone(request.getPhone())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getPhone());
        return new AuthResponse(token, new UserDto(user));
    }

    public AuthResponse loginUser(LoginRequest loginRequest){
        User user = userRepository.findByPhone(loginRequest.getPhone())
                .orElseThrow(() -> new RuntimeException("User with " + loginRequest.getPhone() + " not found"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }
        String token = jwtUtil.generateToken(user.getPhone());
        return new AuthResponse(token, new UserDto(user));
    }

}
