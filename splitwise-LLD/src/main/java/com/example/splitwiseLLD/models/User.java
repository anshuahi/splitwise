package com.example.splitwiseLLD.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User extends BaseModel {
    private String Name;

    private String phone;

    private String password;
}
