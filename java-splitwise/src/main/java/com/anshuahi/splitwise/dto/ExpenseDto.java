package com.anshuahi.splitwise.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
//@NoArgsConstructor
public class ExpenseDto {
    private String description;
    private Long groupId;
    private Long paidBy;
    private Long addedBy;
    private Double totalAmount;
    private List<SplitDto> membersContributed;
    private String splitType;

}
