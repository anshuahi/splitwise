package com.anshuahi.splitwise.repository;

import com.anshuahi.splitwise.model.ExpenseGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<ExpenseGroup, Long> {
    List<ExpenseGroup> findByCreatedById(Long id);
    List<ExpenseGroup> findByUsersId(Long userId);
}
