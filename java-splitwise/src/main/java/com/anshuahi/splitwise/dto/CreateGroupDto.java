package com.anshuahi.splitwise.dto;

import com.anshuahi.splitwise.model.ExpenseGroup;
import com.anshuahi.splitwise.model.User;
import com.anshuahi.splitwise.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateGroupDto {
    private Long groupId;
    private UserDto createdBy;
    private String name;
    private LocalDateTime createdAt;
    private List<UserDto> users;

    public CreateGroupDto(ExpenseGroup request){
        this.groupId = request.getId();
        this.createdAt = request.getCreatedAt();
        this.name = request.getName();
        this.users = request.getUsers().stream().map(UserDto::new).toList();
    }
}
