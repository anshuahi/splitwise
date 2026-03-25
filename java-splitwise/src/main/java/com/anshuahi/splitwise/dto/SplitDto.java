package com.anshuahi.splitwise.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
public class SplitDto {
    private Long memberId;
    private Double amount;
}
