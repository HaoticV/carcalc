package pl.wrzosdev.costs;

import pl.wrzosdev.model.costs.Cost;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CostCalc {
    public List<Cost> costHistory = new ArrayList<>();

    public int allCost() {
        return SumUtils.allCosts(costHistory);
    }

    public Date firstCostDate() {
        return SumUtils.firstCostDate(costHistory);
    }
}
