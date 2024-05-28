package com.thoughtworks.splitwise.domainModel;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Currency;
import java.util.Date;
import java.util.List;
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

    public void splitter(){
        double actualAmount=amount/participants.size();
        participants.forEach(participant -> {participant.setAmountOwned(actualAmount- participant.getAmount());});
    }
}
