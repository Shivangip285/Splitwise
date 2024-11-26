package com.example.splitwise.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.*;

@Data
@Builder
public class ExpenseTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private double amount;
    private Date date;
    private Currency currency;
    private List<Participant> participants;
    @Builder.Default
    private List<SettlementTransaction> settlements=new ArrayList<>();

//    public void splitExpense(){
//        double actualAmount = amount / participants.size();
//        participants.forEach(participant -> participant.setAmountOwed(participant.getAmountPaid() - actualAmount));
//    }

    public void split() {
        double actualAmount = amount / participants.size();

        NavigableSet<Participant> sortedParticipants = new TreeSet<>((a, b) -> {
            return Double.compare(a.getAmountPaid(), b.getAmountPaid());
        });

        participants.forEach(sortedParticipants::add);

        settleExpenses(sortedParticipants, actualAmount);
    }

    private void settleExpenses(NavigableSet<Participant> sortedParticipants, double actualAmount) {
        while (!sortedParticipants.isEmpty()) {
            Participant payer = sortedParticipants.pollLast();
            while(payer.getAmountPaid()!=actualAmount){
                Participant payee = sortedParticipants.pollFirst();
                if(payer != null && payee != null && payee.getAmountPaid()==actualAmount){
                    sortedParticipants.add(payer);
                }

                performExpenseSettlement(payer,payee, actualAmount);

                if (Double.compare(payee.getAmountPaid(), actualAmount) != 0) {
                    sortedParticipants.add(payee);
                }
            }

        }
    }

    private void performExpenseSettlement(Participant payee, Participant payer, double actualAmount) {
        double payeeExcess = payee.getAmountPaid() - actualAmount;
        double payerDeficit = actualAmount - payer.getAmountPaid();
        double settlementAmount = Math.min(payeeExcess, payerDeficit);

        settlements.add(SettlementTransaction.builder()
                .PayeeUserId(payee.getUserId())
                .PayerUserId(payer.getUserId())
                .amount(settlementAmount)
                .build());


        payee.setAmountPaid(payee.getAmountPaid() - settlementAmount);
        payer.setAmountPaid(payer.getAmountPaid() + settlementAmount);
    }
}
