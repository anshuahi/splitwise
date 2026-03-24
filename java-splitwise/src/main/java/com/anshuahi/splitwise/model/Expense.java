package com.anshuahi.splitwise.model;

import com.anshuahi.splitwise.dto.ExpenseDto;
import com.anshuahi.splitwise.dto.SplitDto;
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

    private Long groupId;

    @ManyToOne
    @JoinColumn(name = "added_by")
    private User addedBy;

    @ManyToOne
    @JoinColumn(name = "paid_by")
    private User paidBy;

    private Double totalAmount;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Split> splitList;

    public Expense(){}


    public Expense(ExpenseDto expenseDto, UserRepository userRepository){
        this.setDescription(expenseDto.getDescription());
        this.setGroupId(expenseDto.getGroupId());
        this.setTotalAmount(expenseDto.getTotalAmount());
        User addedByUser = userRepository.findById(expenseDto.getAddedBy()).orElseThrow(
                () -> new RuntimeException("User with id " + expenseDto.getAddedBy() + " not found")
        );
        User paidByUser = userRepository.findById(expenseDto.getPaidBy()).orElseThrow(
                () -> new RuntimeException("User with id " + expenseDto.getPaidBy() + " not found")
        );


        this.setAddedBy(addedByUser);
        this.setPaidBy(paidByUser);
        List<Split> splits = new ArrayList<>();
        for(SplitDto splitdto: expenseDto.getMembersContributed()){
            Split split = new Split();

            split.setAmount(splitdto.getAmount());

            User user = userRepository.findById(splitdto.getMemberId()).orElseThrow(
                    () -> new RuntimeException("User with id " + splitdto.getMemberId() + " not found")
            );
            split.setUser(user);
            split.setExpense(this);
            splits.add(split);
        }
        this.setSplitList(splits);
    }

}
