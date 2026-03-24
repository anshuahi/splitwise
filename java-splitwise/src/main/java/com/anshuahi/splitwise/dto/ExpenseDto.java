package com.anshuahi.splitwise.dto;

import com.anshuahi.splitwise.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    private String description;
    private Long groupId;
    private Long paidBy;
    private Long addedBy;
    private Double totalAmount;
    private List<SplitDto> membersContributed;
    private String splitType;

}
