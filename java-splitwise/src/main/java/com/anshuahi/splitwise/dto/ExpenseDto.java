package com.anshuahi.splitwise.dto;

import com.anshuahi.splitwise.model.Expense;
import lombok.*;

import java.util.List;

@Getter
@Setter
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
