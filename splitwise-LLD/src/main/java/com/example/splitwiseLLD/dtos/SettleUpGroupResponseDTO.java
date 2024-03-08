package com.example.splitwiseLLD.dtos;

import com.example.splitwiseLLD.models.Expense;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SettleUpGroupResponseDTO {
    private List<Expense> expenses;
}
