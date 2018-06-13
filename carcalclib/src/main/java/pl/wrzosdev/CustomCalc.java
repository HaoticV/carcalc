package pl.wrzosdev;

import pl.wrzosdev.model.FuelTank;
import pl.wrzosdev.model.Repair;

import java.util.ArrayList;

public class CustomCalc {
    ArrayList<Repair> repairHistory = new ArrayList<>();
    ArrayList<FuelTank> fuelHistory = new ArrayList<>();

    public int FuelAndRepairCosts() {
        return SumUtils.allCostRepairs(repairHistory) + SumUtils.allCostFuel(fuelHistory);
    }
}
