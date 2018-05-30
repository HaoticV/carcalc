package pl.wrzosdev.model;

import java.util.Date;

public class FuelTank {
    public Date date;
    public Integer mileage;
    public Integer liters;
    public Integer cost;

    public FuelTank(Date date, Integer mileage, Integer liters, Integer cost) {
        this.date = date;
        this.mileage = mileage;
        this.liters = liters;
        this.cost = cost;
    }
}
