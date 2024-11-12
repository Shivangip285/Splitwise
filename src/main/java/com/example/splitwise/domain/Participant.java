package com.example.splitwise.domain;

import lombok.*;

@Data
public class Participant {
    public Participant(String userId, double amountPaid) {
        this.userId = userId;
        this.amountPaid = amountPaid;
    }

    private String userId;

    private double amountPaid;
    private double amountOwed;
}
