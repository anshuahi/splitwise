package com.example.splitwiseLLD.strategies;

import com.example.splitwiseLLD.models.Expense;
import com.example.splitwiseLLD.models.Transaction;

import java.util.List;

public interface SettleUpStrategy {
//    int a = 10;

    List<Transaction> settleUp(List<Expense> expensesToSettle);
}
