package com.anshuahi.splitwise.repository;

import com.anshuahi.splitwise.model.Split;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SplitRepository extends JpaRepository<Split, Long> {
}
