package com.thoughtworks.splitwise.domainModel;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public void splitExpense(){
        double actualAmount=amount/participants.size();
        participants.forEach(participant -> {participant.setAmountOwned(actualAmount- participant.getAmount());});
    }

    public List<Participant> sortBorrowerParticipantsBasedOnOwnedAmount(){
       return participants.stream().filter(x->x.getAmountOwned()>0).sorted((a, b) -> Double.compare(a.getAmountOwned(), b.getAmountOwned())).collect(Collectors.toList());
    }
    public List<Participant> sortLenderParticipantsBasedOnOwnedAmount(){
        return participants.stream().filter(x->x.getAmountOwned()<0).sorted((a, b) -> Double.compare(a.getAmountOwned(), b.getAmountOwned())).collect(Collectors.toList());
    }
}
