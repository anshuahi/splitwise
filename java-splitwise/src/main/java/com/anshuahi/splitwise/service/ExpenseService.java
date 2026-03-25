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
    public Expense createExpense(ExpenseDto expenseDto){
        Expense expense = new Expense();
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
        expenseRepository.save(expense);
        for(SplitDto splitdto: expenseDto.getMembersContributed()){
            Split split = new Split();

            split.setAmount(splitdto.getAmount());

            User user = userRepository.findById(splitdto.getMemberId()).orElseThrow(
                    () -> new RuntimeException("User with id " + splitdto.getMemberId() + " not found")
            );
            split.setUser(user);
            split.setExpense(expense);
            splitRepository.save(split);
        }
        return expense;
    }

    public String addExpense(ExpenseDto expenseRequest){
        Expense expense = createExpense(expenseRequest);
        return "Expense added successfully";
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
            System.out.println(splitList);
            return new ExpenseResponseDto(expense, splitList);
        }
).toList();
    }


}
