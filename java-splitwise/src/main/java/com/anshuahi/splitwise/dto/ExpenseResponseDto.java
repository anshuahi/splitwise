package com.anshuahi.splitwise.dto;

import com.anshuahi.splitwise.model.Expense;
import com.anshuahi.splitwise.model.Split;
import com.anshuahi.splitwise.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponseDto {
    private Long id;
    private String description;
    private UserDto paidBy;
    private UserDto addedBy;
    private Double totalAmount;
    private List<SplitResponseDto> memberContribution;
    private String splitType;


    public ExpenseResponseDto(Expense expense, List<SplitResponseDto> splitList){
        this.id = expense.getId();
        this.description = expense.getDescription();
        this.paidBy = new UserDto(expense.getPaidBy());
        this.addedBy = new UserDto(expense.getAddedBy());
        this.totalAmount = expense.getTotalAmount();
        this.memberContribution = splitList;
        this.splitType = expense.getSplitType();
    }
}
