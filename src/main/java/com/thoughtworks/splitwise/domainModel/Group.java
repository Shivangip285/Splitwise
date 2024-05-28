package com.thoughtworks.splitwise.domainModel;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class Group {
    private String id;
    private String name;
    private List<Participant> participants;
}
