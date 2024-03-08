package com.example.splitwiseLLD.strategies;

import com.example.splitwiseLLD.models.Expense;

import java.util.List;

public interface SettleUpStrategy {
//    int a = 10;

    List<Expense> settleUp(List<Expense> expensesToSettle);
}
