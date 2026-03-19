package com.anshuahi.splitwise.dto;

import com.anshuahi.splitwise.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String phone;
    public UserDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
    }
}
