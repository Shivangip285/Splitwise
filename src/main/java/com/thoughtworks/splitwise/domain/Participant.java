package com.thoughtworks.splitwise.domainModel;

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
    public void setAmountOwed(double amount){
        this.amountOwed = amount;
    }
}
