package com.anshuahi.splitwise.model;

import com.anshuahi.splitwise.dto.ExpenseDto;
import com.anshuahi.splitwise.dto.SplitDto;
import com.anshuahi.splitwise.repository.GroupRepository;
import com.anshuahi.splitwise.repository.UserRepository;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Expense {
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "added_by")
    private User addedBy;

    @ManyToOne
    @JoinColumn(name = "paid_by")
    private User paidBy;

    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private ExpenseGroup expenseGroup;

    public Expense(){}

}
