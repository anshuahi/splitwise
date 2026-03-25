package com.anshuahi.splitwise.repository;

import com.anshuahi.splitwise.model.Split;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SplitRepository extends JpaRepository<Split, Long> {
    List<Split> findByExpenseId(Long id);
}
