package com.thoughtworks.expenseSharingApplication.splitwise.domainModel;

import lombok.Builder;
import lombok.Data;

import java.util.Currency;
import java.util.Date;
import java.util.List;
@Data
@Builder
public class ExpenseTransaction {
    private String id;
    private double amount;
    private Date date;
    private Currency currency;
    private List<Participant> participants;
}
