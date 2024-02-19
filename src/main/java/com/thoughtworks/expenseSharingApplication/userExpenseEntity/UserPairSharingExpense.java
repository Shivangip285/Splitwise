package com.thoughtworks.expenseSharingApplication.userExpenseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserPairSharingExpense {
     private String userLendingExpense;
     private String userOwingExpense;
     private double balance;

     public void revertLenderOwnerForBalanceLessThanZero(){
         String newUserOwingExpense = userOwingExpense;
         userOwingExpense=userLendingExpense;
         userLendingExpense=newUserOwingExpense;
     }

     public boolean isUserPairExactlySame(UserPairSharingExpense userPairSharingExpense){
          return userPairSharingExpense.getUserLendingExpense().equals(userLendingExpense) && userPairSharingExpense.getUserOwingExpense().equals(userOwingExpense);
     }
     
     public boolean isUserPairExactReverse(UserPairSharingExpense userPairSharingExpense){
        return  userPairSharingExpense.getUserLendingExpense().equals(userOwingExpense) && userPairSharingExpense.getUserOwingExpense().equals(userLendingExpense);
     }

     public double setBalance(Boolean isUserPairSame, UserPairSharingExpense userPairSharingExpense){
          return isUserPairSame? balance + userPairSharingExpense.getBalance(): balance-  userPairSharingExpense.getBalance();
     }

     public double getBalance() {
          if(balance<0.0){
               revertLenderOwnerForBalanceLessThanZero();
               balance=Math.abs(balance);
          }
          return balance;
     }
}
