package pl.wrzosdev;

import pl.wrzosdev.model.Cost;
import pl.wrzosdev.model.FuelCost;
import pl.wrzosdev.model.RepairCost;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CustomCalc {
    ArrayList<Cost> costHistory = new ArrayList<>();

    public int FuelAndRepairCosts() {
        return SumUtils.allCostFuel(costHistory) + SumUtils.allCostRepairs(costHistory);
    }

   /* public double monthCostRepairs() {
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
