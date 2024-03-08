package com.example.splitwiseLLD.models;

import java.util.List;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Group extends BaseModel {
    private String name;

    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;

    @ManyToMany
    private List<User> members;
}
