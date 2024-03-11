package com.example.splitwiseLLD.strategies;

import com.example.splitwiseLLD.models.Expense;
import com.example.splitwiseLLD.models.Transaction;
import com.example.splitwiseLLD.models.User;

import java.util.*;

public class GreedySettleUpStrategy implements SettleUpStrategy {
    class Record {
        User user;
        int pendingAmount;

        Record(User usr, int pendingAmount){
            this.user = usr;
            this.pendingAmount = pendingAmount;
        }
    }
    @Override
    public List<Transaction> settleUp(List<Expense> expensesToSettle) {
        Map<User, Integer> extraMoney = new HashMap<>();
        for(Expense expense: expensesToSettle){
            for (User user: expense.getPaidBy().keySet()){
                if(!extraMoney.containsKey(user)){
                    extraMoney.put(user, 0);
                }

                extraMoney.put(user, extraMoney.get(user) + expense.getPaidBy().get(user));
            }

            for (User user: expense.getHadToPay().keySet()){
                if(!extraMoney.containsKey(user)){
                    extraMoney.put(user, 0);
                }

                extraMoney.put(user, extraMoney.get(user) - expense.getHadToPay().get(user));
            }
        }

        Queue<Record> negativeQueue = new ArrayDeque<>();
        Queue<Record> positiveQueue = new ArrayDeque<>();

        for (User usr: extraMoney.keySet()){
            if(extraMoney.get(usr) < 0){
                negativeQueue.add(new Record(usr, extraMoney.get(usr)));
            }
            else {
                positiveQueue.add(new Record(usr, extraMoney.get(usr)));
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        while (!positiveQueue.isEmpty() && !negativeQueue.isEmpty()){
            Record firstNegative = negativeQueue.remove();
            Record firstPositive = positiveQueue.remove();

            if(firstPositive.pendingAmount > Math.abs(firstNegative.pendingAmount)){
                transactions.add(
                        new Transaction(firstNegative.user.toDto(), firstPositive.user.toDto(), Math.abs(firstNegative.pendingAmount))
                );
                positiveQueue.add(
                        new Record(firstPositive.user, firstPositive.pendingAmount - Math.abs(firstNegative.pendingAmount))
                );
            }
            else {
                transactions.add(
                        new Transaction(firstNegative.user.toDto(), firstPositive.user.toDto(), Math.abs(firstPositive.pendingAmount))
                );
                if(firstPositive.pendingAmount - Math.abs(firstNegative.pendingAmount) != 0){
                    positiveQueue.add(
                            new Record(firstPositive.user, firstPositive.pendingAmount - Math.abs(firstNegative.pendingAmount))
                    );
                };
            }
        }
        return transactions;
    }
}
