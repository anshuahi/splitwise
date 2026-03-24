package com.anshuahi.splitwise.dto;

import com.anshuahi.splitwise.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GroupDetailsDto {
    private ExpenseGroupDto expenseGroupDto;
    private List<ExpenseResponseDto> expenses;
}
