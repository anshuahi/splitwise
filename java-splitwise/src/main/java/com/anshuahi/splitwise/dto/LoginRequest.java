package com.anshuahi.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class LoginRequest {
    private String phone;
    private String password;
}
