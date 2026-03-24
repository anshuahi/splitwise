package com.anshuahi.splitwise.repository;

import com.anshuahi.splitwise.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByExpenseGroupId(Long groupId);
}
