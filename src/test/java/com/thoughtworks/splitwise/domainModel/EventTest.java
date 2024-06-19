package com.thoughtworks.splitwise.domainModel;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

class EventTest {
@Test
    public void shouldSettleExpenseForAnEventBetweenParticipant(){
        List<ExpenseTransaction> expenseTransactionList=new ArrayList<>();
    List<Participant> participants=new ArrayList<>();
    Participant participant1 = new Participant("abc", 800);
    Participant participant2 = new Participant("nm", 500);
    Participant participant3 = new Participant("ab", 200);
    participants.addAll(List.of(participant1,participant2,participant3));
        ExpenseTransaction expenseTransaction=ExpenseTransaction.builder().amount(1500).currency((Currency) Currency.getAvailableCurrencies().toArray()[0]).date(Date.from(Instant.now())).participants(participants).build();
      participants.remove(0);
        ExpenseTransaction expenseTransaction1=ExpenseTransaction.builder().amount(1500).currency((Currency) Currency.getAvailableCurrencies().toArray()[0]).date(Date.from(Instant.now())).participants(participants).build();
        expenseTransactionList.add(expenseTransaction);
        expenseTransactionList.add(expenseTransaction1);
        Event event= Event.builder().id("abc").name("trip").expenseTransactions(expenseTransactionList).build();
    System.out.println(event.settleExpense().size());
        event.settleExpense().forEach(x-> System.out.println("abc"));
    }
}