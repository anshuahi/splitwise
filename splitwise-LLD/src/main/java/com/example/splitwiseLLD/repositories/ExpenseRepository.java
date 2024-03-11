package com.example.splitwiseLLD.repositories;

import com.example.splitwiseLLD.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByPaidByContainingOrOwnedByContaining(Long userId, Long userId2);
}
