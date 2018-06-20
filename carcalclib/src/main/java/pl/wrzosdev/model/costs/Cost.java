package pl.wrzosdev.model.costs;

import java.util.Date;

public abstract class Cost {
    public Date date;
    public Integer mileage;
    public Integer cost;

    public Cost(Date date, Integer mileage, Integer cost) {
        this.date = date;
        this.mileage = mileage;
        this.cost = cost;
    }
}
