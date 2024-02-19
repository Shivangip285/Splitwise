package com.thoughtworks.expenseSharingApplication.userExpenseEntity;

import com.thoughtworks.expenseSharingApplication.expenseTypeAndVerfication.ExpenseSharingType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserExpenseDetailEntity {
     private String userIdOfPayingUser;
     private int totalExpense;
     private List<String> userIdsForSplittingUser;
     private ExpenseSharingType expenseSharingType;
     private List<Double> sharePercent;
}
