package pl.wrzosdev.model;

import java.util.Date;

public class Repair extends Cost {
    public Date date;
    public Integer mileage;
    public Integer cost;
    public String description;
    public String workshop; //warsztat
    public Integer phoneNumber;

    public Repair(Date date, Integer mileage, Integer cost, String description, String workshop, Integer phoneNumber) {
        super(date, mileage, cost);
        this.date = date;
        this.mileage = mileage;
        this.cost = cost;
        this.description = description;
        this.workshop = workshop;
        this.phoneNumber = phoneNumber;
    }
}
