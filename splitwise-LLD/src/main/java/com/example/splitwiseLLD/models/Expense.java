package com.example.splitwiseLLD.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter @Entity
public class Expense extends BaseModel {
    private String description;

    private int amount;

    @ManyToOne
    private User createdBy;

//    @OneToMany(mappedBy = "expense")
//    private List<ExpenseUser> expenseUsers;
//
//    @ManyToOne
//    private Group group;
//
//    private ExpenseType expenseType;
    @ElementCollection
    private Map<User, Integer> paidBy;

    @ElementCollection
    private Map<User, Integer> hadToPay;


}

/*
        1       1
    Expense -  User  -> M:1
        M       1


        1       M
    Expense -  ExpenseUser  -> 1:M
        1       1

 */
