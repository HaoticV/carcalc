package pl.wrzosdev;

import pl.wrzosdev.model.FuelTank;
import pl.wrzosdev.model.Repair;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CustomCalc {
    ArrayList<Repair> repairHistory = new ArrayList<>();
    ArrayList<FuelTank> fuelHistory = new ArrayList<>();

    public int FuelAndRepairCosts() {
        return SumUtils.allCostFuel(fuelHistory) + SumUtils.allCostRepairs(repairHistory);
    }

    public double monthCostRepairs() {
        //znajdź datę pierwszego wpisu w aplikacji(tankowanie lub naprawa)
        long firstdate = fuelHistory.get(0).date.getTime();
        if (repairHistory.get(0).date.getTime() < fuelHistory.get(0).date.getTime()) {
            firstdate = repairHistory.get(0).date.getTime();
        }
        long secdate = fuelHistory.get(fuelHistory.size() - 1).date.getTime();
        if (repairHistory.get(repairHistory.size() - 1).date.getTime() > fuelHistory.get(fuelHistory.size() - 1).date.getTime()) {
            secdate = repairHistory.get(repairHistory.size() - 1).date.getTime();
        }
        long time = TimeUnit.MILLISECONDS.toDays(secdate - firstdate);
        return (double) SumUtils.allCostRepairs(repairHistory) / time * 30;
    }
}
