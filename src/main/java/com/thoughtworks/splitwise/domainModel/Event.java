package com.thoughtworks.splitwise.domainModel;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

@Data
@Builder
public class Event {
    private String id;
    private List<ExpenseTransaction> expenseTransactions;
    private String name;
    public List<String> settle(){
        List<String> settlementDetailList=new ArrayList<>();
        PriorityQueue<Participant> participants=new PriorityQueue<Participant>((a,b)->Double.compare(a.getAmountOwned(),b.getAmountOwned()));
        List<Participant> borrowerPaticipentsList = expenseTransactions.stream().map(x -> x.sortBorrowerParticipantsBasedOnOwnedAmount()).flatMap(x->x.stream()).collect(Collectors.toList());
        List<Participant> lenderParticipentsList = expenseTransactions.stream().map(x -> x.sortLenderParticipantsBasedOnOwnedAmount()).flatMap(x->x.stream()).collect(Collectors.toList());
        lenderParticipentsList.forEach(x->participants.offer(x));
        borrowerPaticipentsList.forEach(x->{
            Participant a=participants.poll();
            while(x.getAmountOwned()!=0){
                if(x.getAmountOwned()<a.getAmountOwned()){
                    settlementDetailList.add(x.getUserId()+" have paid back"+ x.getAmountOwned() +" to "+a.getUserId());
                    a.setAmountOwned(a.getAmountOwned()+x.getAmountOwned());
                    x.setAmountOwned(0);
                    participants.offer(a);
                }
                else{
                    settlementDetailList.add(x.getUserId()+" have paid back"+ a.getAmountOwned() +" to "+a.getUserId());
                    x.setAmountOwned(a.getAmountOwned()+x.getAmountOwned());
                }
            }
        });
        return settlementDetailList;
    }
}
