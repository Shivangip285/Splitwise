package com.example.splitwise.domain;

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
    public List<String> settleExpense(){
        List<String> settlementDetailList=new ArrayList<>();
        List<Participant> participants=new ArrayList<>();
        PriorityQueue<Participant> lenderParticipantsList=new PriorityQueue<Participant>((a,b)->Double.compare(a.getAmountOwed(),b.getAmountOwed()));
        List<Participant> borrowerParticipantsList=new ArrayList<>();

        expenseTransactions.stream().forEach(x -> {
            x.splitExpense();
            x.getParticipants().forEach(y->{
                boolean participantPresentInPrevTransectionEvent = participants.stream().anyMatch(p -> p.getUserId().equals(y.getUserId()));
                if(participantPresentInPrevTransectionEvent){
                Participant participant1 = participants.stream().filter(p -> p.getUserId().equals(y.getUserId())).collect(Collectors.toList()).get(0);
                    Participant participant2=participant1;
                    participant2.setAmountOwed(participant1.getAmountOwed()+y.getAmountOwed());
                    participants.set(participants.indexOf(participant1),participant2 );
                }
                else{
                    participants.add(y);
                }
            });
        });
        splitParticipantListBasedInAmountOwned(participants,lenderParticipantsList,borrowerParticipantsList);
        settlementDetailList=getExpenseSettleDetailsList(settlementDetailList,lenderParticipantsList,borrowerParticipantsList);

        settlementDetailList.forEach(x1-> System.out.println(x1));

        return settlementDetailList;
    }
     private void splitParticipantListBasedInAmountOwned(List<Participant> participants,PriorityQueue<Participant> lenderParticipantsList,List<Participant> borrowerParticipantsList){
         for(Participant participant:participants){
             if(participant.getAmountOwed()>0){
                 lenderParticipantsList.add(participant);
             }
             else{
                 borrowerParticipantsList.add(participant);
             }
         }
     }
     private List<String> getExpenseSettleDetailsList(List<String> settlementDetailList,PriorityQueue<Participant> lenderParticipantsList,List<Participant> borrowerParticipantsList){
         while(!lenderParticipantsList.isEmpty()){
             Participant lenderParticipant=lenderParticipantsList.poll();
             while(lenderParticipant.getAmountOwed()!=0){
                 Participant participant = borrowerParticipantsList.get(0);
                 settlementDetailList.add(participant.getUserId()+" has paid back "+(Math.min(lenderParticipant.getAmountOwed(),Math.abs(participant.getAmountOwed())))+" to "+lenderParticipant.getUserId());
                 if(Math.abs(participant.getAmountOwed())<=lenderParticipant.getAmountOwed()){
                     lenderParticipant.setAmountOwed(lenderParticipant.getAmountOwed()+ participant.getAmountOwed());
                     borrowerParticipantsList.remove(0);
                 }
                 else{
                     participant.setAmountOwed(participant.getAmountOwed()+lenderParticipant.getAmountOwed());
                     lenderParticipant.setAmountOwed(0);
                 }
             }
         }
         return settlementDetailList;
     }
}