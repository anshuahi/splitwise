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
    private String description;
    private User paidBy;
    private User addedBy;
    private Double totalAmount;
    private List<Split> memberContribution;
    private String splitType;


    public ExpenseResponseDto(Expense expense){
        this.description = expense.getDescription();
        this.paidBy = expense.getPaidBy();
        this.addedBy = expense.getAddedBy();
        this.totalAmount = expense.getTotalAmount();
//        this.memberContribution = expense.getSplitList();
//        this.splitType = expense.gets
    }
}
