package com.example.splitwiseLLD.models;

import com.example.splitwiseLLD.dtos.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private UserDTO from;
    private UserDTO to;
    private int amount;

    public Transaction(UserDTO from, UserDTO to, int amount){
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
