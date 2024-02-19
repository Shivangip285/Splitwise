package com.thoughtworks.expenseSharingApplication.expenseTypeAndVerfication.shareBasedOnType;

import com.thoughtworks.expenseSharingApplication.expenseTypeAndVerfication.ExpenseSharingType;
import com.thoughtworks.expenseSharingApplication.userExpenseEntity.UserExpenseDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CostSplitterBasedOnExpenseType {

    private UserExpenseDetailEntity userExpenseDetailEntity;

     public CostSharer getCostSharing(){
          int total=userExpenseDetailEntity.getTotalExpense();
          int noOfUser=userExpenseDetailEntity.getUserIdsForSplittingUser().size();

          List<Double> sharePercent=userExpenseDetailEntity.getSharePercent();
          if(userExpenseDetailEntity.getExpenseSharingType().equals(ExpenseSharingType.EXACT)){
               return new CostSharingForTypeExact(total,noOfUser,sharePercent);
          }
          else{
              return userExpenseDetailEntity.getExpenseSharingType().equals(ExpenseSharingType.EQUAL) ? new CostSharingForTypeEqual(total,noOfUser):
              new CostSharingForTypePercent(total,noOfUser,sharePercent);
          }
     }
}
