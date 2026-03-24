package com.anshuahi.splitwise.service;

import com.anshuahi.splitwise.dto.ExpenseDto;
import com.anshuahi.splitwise.dto.SplitDto;
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
        expense.setTotalAmount(expenseDto.getTotalAmount());
        User addedByUser = userRepository.findById(expenseDto.getAddedBy()).orElseThrow(
                () -> new RuntimeException("User with id " + expenseDto.getAddedBy() + " not found")
        );
        User paidByUser = userRepository.findById(expenseDto.getPaidBy()).orElseThrow(
                () -> new RuntimeException("User with id " + expenseDto.getPaidBy() + " not found")
        );

        expense.setAddedBy(addedByUser);
        expense.setPaidBy(paidByUser);
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
        expenseRepository.save(expense);
        return "Expense added successfully";
    }
}
