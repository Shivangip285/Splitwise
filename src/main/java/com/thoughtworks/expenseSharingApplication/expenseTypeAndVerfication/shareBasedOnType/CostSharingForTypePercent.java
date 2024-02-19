package com.thoughtworks.expenseSharingApplication.expenseTypeAndVerfication.shareBasedOnType;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Builder
public class CostSharingForTypePercent extends CostSharer {
    private int total;
    private int noOfUser;
    private List<Double> sharePercent;
    @Override
    public List<Double> getAmountDistribution(){
        List<Double> amount=new ArrayList<>();
        IntStream.range(0, noOfUser).forEach(x -> amount.add(Double.valueOf(total *sharePercent.get(x)/ 100)));
        return amount;
    }
}
