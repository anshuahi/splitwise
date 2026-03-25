package com.anshuahi.splitwise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    private String splitType;

    public Expense(){}

}
