package pl.wrzosdev.costs;

import pl.wrzosdev.model.costs.Cost;

import java.util.ArrayList;

public class CostCalc {
    public ArrayList<Cost> costHistory = new ArrayList<>();

    public int allCost() {
        return SumUtils.allCosts(costHistory);
    }

    /*public double monthCostRepairs() {
        //znajdź datę pierwszego wpisu w aplikacji(tankowanie lub naprawa)
        long firstdate = fuelHistory.get(0).date.getTime();
        if (repairCostHistory.get(0).date.getTime() < fuelHistory.get(0).date.getTime()) {
            firstdate = repairCostHistory.get(0).date.getTime();
        }
        long secdate = fuelHistory.get(fuelHistory.size() - 1).date.getTime();
        if (repairCostHistory.get(repairCostHistory.size() - 1).date.getTime() > fuelHistory.get(fuelHistory.size() - 1).date.getTime()) {
            secdate = repairCostHistory.get(repairCostHistory.size() - 1).date.getTime();
        }
        long time = TimeUnit.MILLISECONDS.toDays(secdate - firstdate);
        return (double) SumUtils.allCostRepairs(repairCostHistory) / time * 30;
    }*/
}
