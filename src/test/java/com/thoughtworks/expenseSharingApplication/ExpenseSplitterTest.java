package com.thoughtworks.expenseSharingApplication;

import com.thoughtworks.expenseSharingApplication.expenseSplitter.ExpenseSplitEntity;
import com.thoughtworks.expenseSharingApplication.expenseSplitter.ExpenseSplitter;
import com.thoughtworks.expenseSharingApplication.expenseTypeAndVerfication.ExpenseSharingType;
import com.thoughtworks.expenseSharingApplication.userDetail.User;
import com.thoughtworks.expenseSharingApplication.userExpenseEntity.UserExpenseDetailEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

class ExpenseSplitterTest {
    @Test
    void shouldPlayGameWithEndStatusAsGameOver() {
       User user1= User.builder().userId("1").name("gcdf").email("aaf").mobileNo(647454574).balance(0.00).build();
       User user2= User.builder().userId("2").name("gcd").email("aag").mobileNo(647454578).balance(0.00).build();
       User user3= User.builder().userId("3").name("gcy").email("aah").mobileNo(647454570).balance(0.00).build();
       User user4= User.builder().userId("4").name("gcg").email("aab").mobileNo(647454579).balance(0.00).build();
       ExpenseSplitEntity expenseSplitEntity=new ExpenseSplitEntity();
       ExpenseSplitter expenseSplitter =new ExpenseSplitter(expenseSplitEntity);

       expenseSplitter.splitExpense(UserExpenseDetailEntity.builder().userIdOfPayingUser(user1.getUserId()).totalExpense(1200).
               userIdsForSplittingUser(List.of(user1.getUserId(),user2.getUserId(),user3.getUserId(),user4.getUserId())).
               expenseSharingType(ExpenseSharingType.PERCENT).sharePercent(List.of(20.00,30.00,23.00,27.00)).build());
       expenseSplitter.splitExpense(UserExpenseDetailEntity.builder().userIdOfPayingUser(user1.getUserId()).totalExpense(1200).
               userIdsForSplittingUser(List.of(user3.getUserId(),user4.getUserId())).expenseSharingType(ExpenseSharingType.PERCENT).
               sharePercent(List.of(50.00,50.00)).build());
       expenseSplitter.splitExpense(UserExpenseDetailEntity.builder().userIdOfPayingUser(user1.getUserId()).totalExpense(1200).
               userIdsForSplittingUser(List.of(user2.getUserId(),user3.getUserId())).expenseSharingType(ExpenseSharingType.EQUAL).
               sharePercent(List.of(1200.00)).build());
       expenseSplitter.splitExpense(UserExpenseDetailEntity.builder().userIdOfPayingUser(user2.getUserId()).totalExpense(1920).
               userIdsForSplittingUser(List.of(user1.getUserId(),user3.getUserId())).expenseSharingType(ExpenseSharingType.EXACT).
               sharePercent(List.of(965.0,955.0)).build());

       expenseSplitter.showExpenseShareForUser(user1.getUserId());
       expenseSplitter.showSplitwiseExpenseForUser();
    }

}