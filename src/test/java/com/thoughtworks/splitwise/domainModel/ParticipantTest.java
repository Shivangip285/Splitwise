package com.thoughtworks.splitwise.domainModel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {
    @Test
   public void shouldSetAmountOwned(){
       Participant participant=new Participant("abc",500.5);
       participant.setAmountOwned(299.5);

        assertEquals(participant.getAmountOwned(),299.5);
   }
}