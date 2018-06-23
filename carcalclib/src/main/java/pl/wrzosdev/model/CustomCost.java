package pl.wrzosdev.model;

import pl.wrzosdev.model.costs.Cost;

import java.util.Date;

/**
 * Custom cost like insurance or car wash
 */
public class CustomCost extends Cost {
    public String comment;

    public CustomCost(Date date, Integer mileage, Integer cost, String comment) {
        super(date, mileage, cost);
        this.comment = comment;
    }
}
