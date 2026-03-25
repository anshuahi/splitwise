package com.anshuahi.splitwise.controller;

import com.anshuahi.splitwise.dto.ExpenseDto;
import com.anshuahi.splitwise.dto.ExpenseResponseDto;
import com.anshuahi.splitwise.model.Expense;
import com.anshuahi.splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add-expense")
    public ResponseEntity<String> addNewExpense(@RequestBody ExpenseDto dto){
        System.out.println(dto);
        return ResponseEntity.ok(expenseService.addExpense(dto));
    }

    @GetMapping("/group-expenses/{id}")
    public ResponseEntity<List<ExpenseResponseDto>> getExpenses(@PathVariable Long id){
        return ResponseEntity.ok(expenseService.getExpenses(id));
    }
}
