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
    List<Participant> payees = new ArrayList<>();
    List<Participant> payers = new ArrayList<>();

    participants.forEach(participant -> {
        if (participant.getAmountPaid() > actualAmount) {
            payees.add(participant);
        } else {
            payers.add(participant);
        }
    });

    expenseSettlement(payees, payers, actualAmount);
}

    private void expenseSettlement(List<Participant> payees, List<Participant> payers, double actualAmount) {
        int payeeIndex = 0,payerIndex = 0;
        while (payeeIndex < payees.size() && payerIndex < payers.size()) {
            Participant payee = payees.get(payeeIndex);
            Participant payer = payers.get(payerIndex);

            performExpenseSettlement(payee, payer, actualAmount);

            if (payee.getAmountPaid()== actualAmount) {
                payeeIndex++;
            }
            if (payer.getAmountPaid()== actualAmount) {
                payerIndex++;
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
