package com.anshuahi.splitwise.repository;

import com.anshuahi.splitwise.model.ExpenseGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<ExpenseGroup, Long> {
}
