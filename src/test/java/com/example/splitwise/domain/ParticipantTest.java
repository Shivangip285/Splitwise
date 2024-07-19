package com.example.splitwise.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {
    @Test
   public void shouldSetAmountOwned(){
       Participant participant=new Participant("abc",500.5);
       participant.setAmountOwed(299.5);

        assertEquals(participant.getAmountOwed(),299.5);
   }
}