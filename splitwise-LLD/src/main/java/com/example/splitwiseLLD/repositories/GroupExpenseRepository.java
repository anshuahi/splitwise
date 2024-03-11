package com.example.splitwiseLLD.repositories;

import com.example.splitwiseLLD.models.Expense;
import com.example.splitwiseLLD.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupExpenseRepository extends JpaRepository<Expense, Long> {
}
