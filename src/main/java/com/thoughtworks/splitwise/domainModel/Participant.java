package com.thoughtworks.expenseSharingApplication.splitwise.domainModel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Participant {
    private String id;
    private String name;
    private String email;
    private double amount;
    private String role;
}
