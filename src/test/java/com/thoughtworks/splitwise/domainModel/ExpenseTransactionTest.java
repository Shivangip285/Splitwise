package com.thoughtworks.splitwise.domainModel;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseTransactionTest {
  @Test
  public void shouldSplitAmountAndUpdateOwnedAmountForEachParticipants(){
     List<Participant> participants=new ArrayList<>();
      Participant participant1 = new Participant("abc", 800);
      Participant participant2 = new Participant("nm", 500);
      Participant participant3 = new Participant("ab", 200);
      participants.addAll(List.of(participant1,participant2,participant3));

      ExpenseTransaction expenseTransaction=ExpenseTransaction.builder().amount(1500).currency((Currency) Currency.getAvailableCurrencies().toArray()[0]).date(Date.from(Instant.now())).participants(participants).build();
      ExpenseTransaction expenseTransaction1=ExpenseTransaction.builder().amount(1500).currency((Currency) Currency.getAvailableCurrencies().toArray()[0]).date(Date.from(Instant.now())).participants(participants).build();

      expenseTransaction.splitExpense();
      expenseTransaction1.splitExpense();

      assertEquals(participant1.getAmountOwned(),-600);
      assertEquals(participant2.getAmountOwned(),0);
      assertEquals(participant3.getAmountOwned(),600);
  }
}