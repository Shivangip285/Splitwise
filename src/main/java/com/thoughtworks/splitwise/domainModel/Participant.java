package com.thoughtworks.splitwise.domainModel;

import lombok.*;

@Data
public class Participant {
    public Participant(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    private String userId;

    private double amount;
    private double amountOwned;
    public void setAmountOwned(double amount){
        this.amountOwned+=amount;
    }
}
