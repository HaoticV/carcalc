package pl.wrzosdev.model;

import pl.wrzosdev.model.costs.Cost;

import java.util.Date;

public class RepairCost extends Cost {
    public Date date;
    public Integer mileage;
    public Integer cost;
    public String description;
    public String workshop;
    public Integer phoneNumber;

    public RepairCost(Date date, Integer mileage, Integer cost, String description, String workshop, Integer phoneNumber) {
        super(date, mileage, cost);
        this.date = date;
        this.mileage = mileage;
        this.cost = cost;
        this.description = description;
        this.workshop = workshop;
        this.phoneNumber = phoneNumber;
    }
}
