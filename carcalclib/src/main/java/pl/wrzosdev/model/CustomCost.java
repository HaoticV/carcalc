package pl.wrzosdev.model;

import pl.wrzosdev.model.costs.Cost;

import java.util.Date;

public class CustomCost extends Cost {
    public CustomCost(Date date, Integer mileage, Integer cost) {
        super(date, mileage, cost);
    }
}
