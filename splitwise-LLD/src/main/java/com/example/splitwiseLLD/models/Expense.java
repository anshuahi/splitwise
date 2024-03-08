package com.example.splitwiseLLD.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Entity
public class Expense extends BaseModel {
    private String description;

    private int amount;

    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "expense")
    private List<ExpenseUser> expenseUsers;

    @ManyToOne
    private Group group;

    private ExpenseType expenseType;

}

/*
        1       1
    Expense -  User  -> M:1
        M       1


        1       M
    Expense -  ExpenseUser  -> 1:M
        1       1

 */
