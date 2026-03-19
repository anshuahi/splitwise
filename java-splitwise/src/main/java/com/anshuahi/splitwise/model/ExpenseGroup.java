package com.anshuahi.splitwise.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class ExpenseGroup {
    @Id
    @GeneratedValue
    private Long id;
    private Long createdBy;
    private String name;
    private Date createdAt;

    @ManyToMany
    @JoinTable(
            name = "group_users",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userList;
}
