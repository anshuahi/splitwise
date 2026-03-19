package com.anshuahi.splitwise.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Split {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;
}
