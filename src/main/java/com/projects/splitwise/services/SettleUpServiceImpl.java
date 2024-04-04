package com.projects.splitwise.services;

import com.projects.splitwise.exceptions.InvalidRequestException;
import com.projects.splitwise.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettleUpServiceImpl implements SettleUpService {

    @Override
    public List<Transaction> settleGroup(int groupId) throws InvalidRequestException {
        return null;
    }

}
