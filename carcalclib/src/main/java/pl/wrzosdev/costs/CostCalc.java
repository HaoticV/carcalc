package pl.wrzosdev.costs;

import pl.wrzosdev.model.costs.Cost;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

public class CostCalc {
    public ArrayList<Cost> costHistory = new ArrayList<>();

    public int allCost() {
        return SumUtils.allCosts(costHistory);
    }

    /*public double monthCostRepairs() {

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

    //znajdź datę pierwszego wpisu w aplikacji(tankowanie lub naprawa)
    public Date firstDateReg() {
        Optional<Cost> optionalCost = costHistory.stream().min(Comparator.comparing(cost -> cost.date));
        long currentDateTime = System.currentTimeMillis();
        Date currentDate = new Date(currentDateTime);
        if (optionalCost.isPresent())
            return optionalCost.get().date;
        else
            return currentDate;
    }
}
