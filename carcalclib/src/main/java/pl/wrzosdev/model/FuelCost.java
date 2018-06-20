package pl.wrzosdev.model;

import java.util.Date;

public class FuelCost extends Cost {
    public Date date;
    public Integer mileage;
    public Integer cost;
    public Integer liters;

    public FuelCost(Date date, Integer mileage, Integer cost, Integer liters) {
        super(date, mileage, cost);
        this.date = date;
        this.mileage = mileage;
        this.cost = cost;
        this.liters = liters;
    }
}
