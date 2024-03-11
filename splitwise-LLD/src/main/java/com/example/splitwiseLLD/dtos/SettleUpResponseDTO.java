package com.example.splitwiseLLD.dtos;


import com.example.splitwiseLLD.models.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpResponseDTO {
    private List<Transaction> transactions;
}
