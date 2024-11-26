package com.example.splitwise.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseTransactionTest {
  @Test
  public void shouldSplitExpenseAmountAndUpdateSettlementListForParticipantsForMultiplePayee(){
     List<Participant> participants=new ArrayList<>();
      Participant participant1 = new Participant("abc", 700);
      Participant participant2 = new Participant("nm", 600);
      Participant participant3 = new Participant("ab", 200);
      participants.addAll(List.of(participant1,participant2,participant3));

      ExpenseTransaction expenseTransaction=ExpenseTransaction.builder().amount(1500).currency((Currency) Currency.getAvailableCurrencies().toArray()[0]).date(Date.from(Instant.now())).participants(participants).build();

      expenseTransaction.split();

      assertEquals(expenseTransaction.getSettlements().size(),2);
      assertEquals(expenseTransaction.getSettlements().get(0).getPayeeUserId(),"abc");
      assertEquals(expenseTransaction.getSettlements().get(0).getPayerUserId(),"ab");
      assertEquals(expenseTransaction.getSettlements().get(0).getAmount(),200.0);
      assertEquals(expenseTransaction.getSettlements().get(1).getAmount(),100.0);
  }

    @Test
    public void shouldHaveNoSettlementForExpenseSplitEquallyAmongParticipants(){
        List<Participant> participants=new ArrayList<>();
        Participant participant1 = new Participant("abc", 500);
        Participant participant2 = new Participant("nm", 500);
        Participant participant3 = new Participant("ab", 500);
        participants.addAll(List.of(participant1,participant2,participant3));

        ExpenseTransaction expenseTransaction=ExpenseTransaction.builder().amount(1500).currency((Currency) Currency.getAvailableCurrencies().toArray()[0]).date(Date.from(Instant.now())).participants(participants).build();

        expenseTransaction.split();

        assertEquals(expenseTransaction.getSettlements().size(),0);
    }

    @Test
    public void shouldSplitExpenseAmountAndUpdateSettlementListForParticipantsForSinglePayee(){
        List<Participant> participants=new ArrayList<>();
        Participant participant1 = new Participant("abc", 750);
        Participant participant2 = new Participant("nm", 400);
        Participant participant3 = new Participant("ab", 350);
        participants.addAll(List.of(participant1,participant2,participant3));

        ExpenseTransaction expenseTransaction=ExpenseTransaction.builder().amount(1500).currency((Currency) Currency.getAvailableCurrencies().toArray()[0]).date(Date.from(Instant.now())).participants(participants).build();

        expenseTransaction.split();

        assertEquals(expenseTransaction.getSettlements().size(),2);
        assertEquals(expenseTransaction.getSettlements().get(0).getPayeeUserId(),"abc");
        assertEquals(expenseTransaction.getSettlements().get(0).getPayerUserId(),"ab");
        assertEquals(expenseTransaction.getSettlements().get(1).getPayerUserId(),"nm");
        assertEquals(expenseTransaction.getSettlements().get(0).getAmount(),150.0);
        assertEquals(expenseTransaction.getSettlements().get(1).getAmount(),100.0);
    }
}