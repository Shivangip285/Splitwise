package com.thoughtworks.expenseSharingApplication.expenseTypeAndVerfication;

import com.thoughtworks.expenseSharingApplication.expenseTypeAndVerfication.shareBasedOnType.CostSharer;

import java.util.Optional;


public class ExpenseVerifier {
     public  boolean isExpenseSplitValid(CostSharer amountEntities, int total){
          Optional<Double> reduce = amountEntities.getAmountDistribution().stream().reduce((a, b) -> a + b);
          return reduce.get()==total;
     }
}
