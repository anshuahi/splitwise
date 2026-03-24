package com.anshuahi.splitwise.service;

import com.anshuahi.splitwise.dto.ExpenseDto;
import com.anshuahi.splitwise.model.Expense;
import com.anshuahi.splitwise.repository.ExpenseRepository;
import com.anshuahi.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;


    public String addExpense(ExpenseDto expenseRequest){
        Expense expense = new Expense(expenseRequest, userRepository);
        expenseRepository.save(expense);
        return "Expense added successfully";
    }
}
