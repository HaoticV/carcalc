package pl.wrzosdev.model;

import java.util.Date;

public class FuelTank extends Cost {
    public Date date;
    public Integer mileage;
    public Integer cost;
    public Integer liters;

    public FuelTank(Date date, Integer mileage, Integer cost, Integer liters) {
        super(date, mileage, cost);
        this.date = date;
        this.mileage = mileage;
        this.cost = cost;
        this.liters = liters;
    }
}
