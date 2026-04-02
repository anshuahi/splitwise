package com.anshuahi.splitwise.service;

import com.anshuahi.splitwise.dto.*;
import com.anshuahi.splitwise.model.Expense;
import com.anshuahi.splitwise.model.ExpenseGroup;
import com.anshuahi.splitwise.model.Split;
import com.anshuahi.splitwise.model.User;
import com.anshuahi.splitwise.repository.ExpenseRepository;
import com.anshuahi.splitwise.repository.GroupRepository;
import com.anshuahi.splitwise.repository.SplitRepository;
import com.anshuahi.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SplitRepository splitRepository;

    @Transactional
    public String upsertExpense(ExpenseDto expenseDto){
        Expense expense;

        // 1. fetch
        if(expenseDto.getId() != null){
            expense = expenseRepository.findById(expenseDto.getId())
                    .orElseThrow(() -> new RuntimeException("Expense not found"));
//            expense.getSplits().clear();
        }
        else {
            expense = new Expense();
        }
        expense.setDescription(expenseDto.getDescription());
        ExpenseGroup expenseGroup = groupRepository.findById(expenseDto.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));
        expense.setExpenseGroup(expenseGroup);
        expense.setSplitType(expenseDto.getSplitType());
        expense.setTotalAmount(expenseDto.getTotalAmount());
        User addedByUser = userRepository.findById(expenseDto.getAddedBy()).orElseThrow(
                () -> new RuntimeException("User with id " + expenseDto.getAddedBy() + " not found")
        );
        User paidByUser = userRepository.findById(expenseDto.getPaidBy()).orElseThrow(
                () -> new RuntimeException("User with id " + expenseDto.getPaidBy() + " not found")
        );

        expense.setAddedBy(addedByUser);
        expense.setPaidBy(paidByUser);
        List<Split> splitList = expenseDto.getMembersContributed().stream().map(
                member -> {
                    User user = userRepository.findById(member.getMemberId()).orElseThrow(
                            () -> new RuntimeException("User with id " + member.getMemberId() + " not found")
                    );

                    Split split = new Split();
                    split.setAmount(member.getAmount());
                    split.setUser(user);
                    split.setExpense(expense);
                    return split;
                }
        ).toList();
        if(expenseDto.getId() == null){
            expense.setSplits(splitList);
        }
        else {
            expense.getSplits().clear();
            expense.getSplits().addAll(splitList);
        }
        expenseRepository.save(expense);
        return expenseDto.getId() != null
                ? "Expense updated successfully"
                : "Expense added successfully";
    }

    public List<ExpenseResponseDto> getExpenses(Long groupId){
        List<Expense> expenseList = expenseRepository.findByExpenseGroupId(groupId);
        return expenseList.stream().map(
        expense -> {
            List<SplitResponseDto> splitList = splitRepository.findByExpenseId(expense.getId())
                    .stream().map(
                            split -> {
                                return new SplitResponseDto(split.getId(), new UserDto(split.getUser()), split.getAmount());
                            }
                    ).toList();
            return new ExpenseResponseDto(expense, splitList);
        }
).toList();
    }

    @Transactional
    public String deleteExpense(Long expenseId){
        Expense expense = expenseRepository.findById(expenseId)
                        .orElseThrow(() -> new RuntimeException("Expense not found"));
        expenseRepository.delete(expense);
        return "Expense deleted successfully";
    }
}
