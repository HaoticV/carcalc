package pl.wrzosdev.costs;

import pl.wrzosdev.model.costs.Cost;

import java.util.ArrayList;
import java.util.Date;

public class CostCalc {
    public static ArrayList<Cost> costHistory = new ArrayList<>();

    public int allCost() {
        return SumUtils.allCosts(costHistory);
    }

    public static Date firstDateRegistered() {
        return SumUtils.firstDateRegistered(costHistory);
    }
}
