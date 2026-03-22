package com.anshuahi.splitwise.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
public class SplitDto {
    private Long memberId;
    private Double amount;
}
