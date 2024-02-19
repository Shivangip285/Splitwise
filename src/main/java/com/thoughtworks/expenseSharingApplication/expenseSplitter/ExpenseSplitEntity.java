package com.thoughtworks.expenseSharingApplication.expenseSplitter;

import com.thoughtworks.expenseSharingApplication.userExpenseEntity.UserPairSharingExpense;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class ExpenseSplitEntity {
     private UserPairSharingExpense userPairSharingExpense;

     public ExpenseSplitEntity() {
          this.allUserPairExpenseShare = new ArrayList<UserPairSharingExpense>();
     }

     private List<UserPairSharingExpense> allUserPairExpenseShare;

     public void add(UserPairSharingExpense userPairSharingExpense){
          allUserPairExpenseShare.add(userPairSharingExpense);
     }
     public void updateBalanceForUserPair(UserPairSharingExpense userPairSharingExpense){
          AtomicBoolean isUserPairPresent= new AtomicBoolean(false);
               allUserPairExpenseShare.forEach(x -> {
                    boolean userPairExactlySame = userPairSharingExpense.isUserPairExactlySame(x);
                    if ((userPairExactlySame || userPairSharingExpense.isUserPairExactReverse(x)) && !allUserPairExpenseShare.isEmpty()){
                        updateBalanceForExistingUserPair(userPairExactlySame,x,userPairSharingExpense);
                        System.out.println(x.getUserOwingExpense() + x.getUserLendingExpense() + x.getBalance());
                        isUserPairPresent.set(true);
                    }
               });
               if((isUserPairPresent.get()==false ) || allUserPairExpenseShare.isEmpty()){
                    add(userPairSharingExpense);
               }
     }
     public void updateBalanceForExistingUserPair(boolean isUserPairExactlySame,UserPairSharingExpense userPairSharingExpense1,UserPairSharingExpense userPairSharingExpense2){
          if (isUserPairExactlySame) {
               userPairSharingExpense1.setBalance(userPairSharingExpense1.getBalance() + userPairSharingExpense2.getBalance());
          } else {
               userPairSharingExpense1.setBalance(userPairSharingExpense1.getBalance() - userPairSharingExpense2.getBalance());
          }
     }

     public void remove(UserPairSharingExpense userPairSharingExpense){
        allUserPairExpenseShare.remove(userPairSharingExpense);
     }
}
