package com.anshuahi.splitwise.controller;

import com.anshuahi.splitwise.dto.ExpenseDto;
import com.anshuahi.splitwise.dto.ExpenseResponseDto;
import com.anshuahi.splitwise.model.Expense;
import com.anshuahi.splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // UPSERT expense
    @PostMapping
    public ResponseEntity<String> upsertExpense(@RequestBody ExpenseDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.upsertExpense(dto));
    }

    // GET expenses by group
    @GetMapping("/groups/{groupId}")
    public ResponseEntity<List<ExpenseResponseDto>> getExpensesByGroup(@PathVariable Long groupId){
        return ResponseEntity.ok(expenseService.getExpenses(groupId));
    }

    // DELETE expense
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id){
        return ResponseEntity.ok(expenseService.deleteExpense(id));
    }
}
