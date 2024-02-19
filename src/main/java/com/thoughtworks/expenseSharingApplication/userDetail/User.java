package com.thoughtworks.expenseSharingApplication.userDetail;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
@Data
@Builder
public class User {
    @NonNull
    private String userId;
    @NonNull
    private String name;

    private String email;

    private long mobileNo;

    private double balance;
}
