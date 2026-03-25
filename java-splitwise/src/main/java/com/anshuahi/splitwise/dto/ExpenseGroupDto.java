package com.anshuahi.splitwise.dto;

import com.anshuahi.splitwise.model.ExpenseGroup;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExpenseGroupDto {
    private Long id;
    private UserDto createdBy;
    private List<UserDto> members;
    private String groupName;
    private LocalDateTime createdAt;

    public ExpenseGroupDto(ExpenseGroup group) {
        this.id = group.getId();
        this.createdAt = group.getCreatedAt();
        this.groupName = group.getName();
        this.members = group.getUsers().stream().map(UserDto::new).toList();
        this.createdBy = new UserDto(group.getCreatedBy());
    }
}
