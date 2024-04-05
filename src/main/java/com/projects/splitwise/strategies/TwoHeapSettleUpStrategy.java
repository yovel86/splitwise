package com.projects.splitwise.strategies;

import com.projects.splitwise.models.Transaction;
import com.projects.splitwise.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Component
public class TwoHeapSettleUpStrategy implements SettleUpStrategy {

    @Override
    public List<Transaction> settleUp(Map<User, Double> userTotal) {

        PriorityQueue<UserTransaction> maxHeap = new PriorityQueue<>((o1, o2) -> (int)(o1.getAmount() - (int)o2.getAmount()));
        PriorityQueue<UserTransaction> minHeap = new PriorityQueue<>((o1, o2) -> (int)(o2.getAmount() - (int)o1.getAmount()));

        for (Map.Entry<User, Double> entry : userTotal.entrySet()) {
            if(entry.getValue() > 0) {
                maxHeap.add(new UserTransaction(entry.getKey(), entry.getValue()));
            } else {
                minHeap.add(new UserTransaction(entry.getKey(), entry.getValue()));
            }
        }

        List<Transaction> transactions = new ArrayList<>();
        while(!minHeap.isEmpty() && !maxHeap.isEmpty()) {
            UserTransaction userToPayMoney = minHeap.poll();
            UserTransaction userToGetMoney = maxHeap.poll();
            double amountToBeTransferred = Math.min(Math.abs(userToPayMoney.getAmount()), userToGetMoney.getAmount());
            Transaction transaction = new Transaction();
            transaction.setAmount(amountToBeTransferred);
            transaction.setPairFrom(userToPayMoney.getUser());
            transaction.setPaidTo(userToGetMoney.getUser());
            // 1000 - 500 = 500 (need to put this balance back into maxHeap)
            if(userToGetMoney.getAmount() - amountToBeTransferred > 0) {
                maxHeap.add(new UserTransaction(userToGetMoney.getUser(), userToGetMoney.getAmount() - amountToBeTransferred));
            }
            // -1000 + 500 = -500 (need to put this balance back into minHeap)
            if(userToPayMoney.getAmount() + amountToBeTransferred < 0) {
                minHeap.add(new UserTransaction(userToPayMoney.getUser(), userToPayMoney.getAmount() + amountToBeTransferred));
            }
        }

        return transactions;

    }

}
