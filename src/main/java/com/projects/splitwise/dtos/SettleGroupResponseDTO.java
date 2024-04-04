package com.projects.splitwise.dtos;

import com.projects.splitwise.models.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class SettleGroupResponseDTO {

    private List<Transaction> transactions;
    private Response response;

}
