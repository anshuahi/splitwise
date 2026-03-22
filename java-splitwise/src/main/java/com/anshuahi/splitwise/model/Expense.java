package com.anshuahi.splitwise.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Expense {
    @Id
    @GeneratedValue
    private Long id;

//    @ManyToOne()
//    private Long userId

    private Long groupId;

    private Double totalAmount;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Split> splitList;
}
