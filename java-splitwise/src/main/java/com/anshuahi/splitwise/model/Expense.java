package com.anshuahi.splitwise.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Split> splits;

}
