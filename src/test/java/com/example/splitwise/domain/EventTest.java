package com.example.splitwise.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;

class EventTest {
@Test
    public void shouldSettleExpenseForAnEventBetweenParticipant(){
        List<ExpenseTransaction> expenseTransactionList=new ArrayList<>();
    List<Participant> participants=new ArrayList<>();
    Participant participant1 = new Participant("abc", 800);
    Participant participant2 = new Participant("nm", 500);
    Participant participant3 = new Participant("ab", 200);
    participants.addAll(List.of(participant1,participant2,participant3));
        ExpenseTransaction expenseTransaction=ExpenseTransaction.builder().id("a").amount(1500).currency((Currency) Currency.getAvailableCurrencies().toArray()[0]).date(Date.from(Instant.now())).participants(participants).build();
        ExpenseTransaction expenseTransaction1=ExpenseTransaction.builder().amount(1300).currency((Currency) Currency.getAvailableCurrencies().toArray()[0]).date(Date.from(Instant.now())).participants(List.of(new Participant("abc", 800),new Participant("nm", 500))).build();
        expenseTransactionList.add(expenseTransaction);
        expenseTransactionList.add(expenseTransaction1);
        Event event= Event.builder().id("abc").name("trip").expenseTransactions(expenseTransactionList).build();
        event.settleExpense();
    }

    @Test
    public void shouldSettleExpenseForAnEventBetweenMoreThanFiveParticipant(){
        List<ExpenseTransaction> expenseTransactionList=new ArrayList<>();
        List<Participant> participants=new ArrayList<>();
        Participant participant1 = new Participant("abc", 800);
        Participant participant2 = new Participant("nm", 500);
        Participant participant3 = new Participant("ab", 200);
        Participant participant4 = new Participant("pq", 850);
        Participant participant5 = new Participant("xy", 150);
        participants.addAll(List.of(participant1,participant2,participant3,participant4,participant5));
        ExpenseTransaction expenseTransaction=ExpenseTransaction.builder().id("a").amount(2500).currency((Currency) Currency.getAvailableCurrencies().toArray()[0]).date(Date.from(Instant.now())).participants(participants).build();
        ExpenseTransaction expenseTransaction1=ExpenseTransaction.builder().amount(1500).currency((Currency) Currency.getAvailableCurrencies().toArray()[0]).date(Date.from(Instant.now())).participants(List.of(new Participant("abc", 800),new Participant("nm", 500),new Participant("pq", 200))).build();
        expenseTransactionList.add(expenseTransaction);
        expenseTransactionList.add(expenseTransaction1);
        Event event= Event.builder().id("abc1").name("trip").expenseTransactions(expenseTransactionList).build();
        event.settleExpense();
    }
}