package com.thoughtworks.expenseSharingApplication.splitwise.domainModel;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class Event {
    private String id;
    private List<ExpenseTransaction> expenseTransactions;
    private String name;
}
