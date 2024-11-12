package com.example.splitwise.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SettlementTransaction {
    private String PayeeUserId;
    private String PayerUserId;
    private double amount;
}
