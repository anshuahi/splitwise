package com.anshuahi.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SplitResponseDto {
    private Long id;
    private UserDto user;
    private Double amount;
}
