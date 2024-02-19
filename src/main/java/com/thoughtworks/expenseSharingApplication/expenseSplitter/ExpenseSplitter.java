package com.thoughtworks.expenseSharingApplication.expenseSplitter;

import com.thoughtworks.expenseSharingApplication.expenseSplitter.ExpenseSplitEntity;
import com.thoughtworks.expenseSharingApplication.expenseTypeAndVerfication.ExpenseVerifier;
import com.thoughtworks.expenseSharingApplication.expenseTypeAndVerfication.shareBasedOnType.CostSplitterBasedOnExpenseType;
import com.thoughtworks.expenseSharingApplication.userExpenseEntity.UserExpenseDetailEntity;
import com.thoughtworks.expenseSharingApplication.userExpenseEntity.UserPairSharingExpense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ExpenseSplitter {

ExpenseSplitEntity expenseSplitEntity;

     public void splitExpense(UserExpenseDetailEntity userExpenseDetailEntity){
          CostSplitterBasedOnExpenseType amountEntities=new CostSplitterBasedOnExpenseType(userExpenseDetailEntity);
          ExpenseVerifier expenseVerifier=new ExpenseVerifier();
          if(expenseVerifier.isExpenseSplitValid(amountEntities.getCostSharing(),userExpenseDetailEntity.getTotalExpense())){
               userExpenseDetailEntity.getUserIdsForSplittingUser().forEach(x->{
                    UserPairSharingExpense userPairSharingExpense = new UserPairSharingExpense(userExpenseDetailEntity.getUserIdOfPayingUser(), x, amountEntities.getCostSharing().getAmountDistribution().get(userExpenseDetailEntity.getUserIdsForSplittingUser().indexOf(x)));
                    if(!userPairSharingExpense.getUserOwingExpense().equals(userPairSharingExpense.getUserLendingExpense())){
                        expenseSplitEntity.updateBalanceForUserPair(userPairSharingExpense);
                    }
               });
          }
     }

     public void showExpenseShareForUser(String userId){
          expenseSplitEntity.getAllUserPairExpenseShare().forEach(x->{
               if(x.getUserLendingExpense().equals(userId)){
                    System.out.println(x.getUserOwingExpense()+" "+x.getBalance());
               }
          });
     }
     public void showSplitwiseExpenseForUser(){
          expenseSplitEntity.getAllUserPairExpenseShare().stream().filter(x->x.getBalance()!=0.0).
                  forEach(x-> System.out.println(x.getUserLendingExpense()+" "+x.getUserOwingExpense()+" "+x.getBalance()));
     }
}
