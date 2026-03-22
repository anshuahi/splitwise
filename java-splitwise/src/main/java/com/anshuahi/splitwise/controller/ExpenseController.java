package com.anshuahi.splitwise.controller;

import com.anshuahi.splitwise.dto.ExpenseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    @PostMapping("/add-expense")
    public ResponseEntity<String> addNewExpense(@RequestBody ExpenseDto dto){
        System.out.println(dto);
        return ResponseEntity.ok("Added expense");
    }
}
