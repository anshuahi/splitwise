package com.example.splitwiseLLD.models;

import com.example.splitwiseLLD.dtos.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User extends BaseModel {
    private String name;

    private String phone;

    private String password;

    public User(String name, String phone, String password){
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public User(){

    }

    public UserDTO toDto() {
        UserDTO userDTO = new UserDTO(name, password);
        return userDTO;
    }
}
