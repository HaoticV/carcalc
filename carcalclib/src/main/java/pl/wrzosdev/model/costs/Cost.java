package pl.wrzosdev.model.costs;

import com.sun.istack.internal.Nullable;

import java.util.Date;

/**
 * Single Cost representation
 */
public abstract class Cost {
    public Date date;
    /**
     * Milage, nigdy nie moze byc nullem
     */
    @Nullable
    public Integer mileage;
    public Integer cost;

    public Cost(Date date, Integer mileage, Integer cost) {
        this.date = date;
        this.mileage = mileage;
        this.cost = cost;
    }
}
