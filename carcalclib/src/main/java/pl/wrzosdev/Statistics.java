package pl.wrzosdev;

import pl.wrzosdev.costs.CostCalc;
import pl.wrzosdev.costs.custom.CustomCalc;
import pl.wrzosdev.costs.fuel.FuelCalc;
import pl.wrzosdev.costs.repair.RepairCalc;
import pl.wrzosdev.model.CustomCost;
import pl.wrzosdev.model.FuelCost;
import pl.wrzosdev.model.RepairCost;
import pl.wrzosdev.model.costs.Cost;

import java.util.ArrayList;

public class Statistics {
    static FuelCalc fuelCalc;
    static RepairCalc repairCalc;
    static CustomCalc customCalc;

    static CostCalc costCalc;

    public static void init(ArrayList<Cost> costsHistory) {
        initAllCosts(costsHistory);
        initSpecificCosts(costsHistory);
    }

    private static void initSpecificCosts(ArrayList<Cost> costsHistory) {
        fuelCalc = new FuelCalc();
        repairCalc = new RepairCalc();
        customCalc = new CustomCalc();
        for (Cost cost : costsHistory) {
            if (cost instanceof FuelCost) fuelCalc.fuelHistory.add((FuelCost) cost);
            if (cost instanceof RepairCost) repairCalc.repairHistory.add((RepairCost) cost);
            if (cost instanceof CustomCost) customCalc.customHistory.add((CustomCost) cost);
        }
    }

    private static void initAllCosts(ArrayList<Cost> costsHistory) {
        costCalc = new CostCalc();
        costCalc.costHistory.addAll(costsHistory);
    }

    public static FuelCalc getFuelCalc() {
        if (fuelCalc.fuelHistory.isEmpty()) {
            throw new IllegalStateException("Nie zainicjalizowałeś historii tankowania!");
        } else {
            return fuelCalc;
        }
    }

    public static RepairCalc getRepairCalc() {
        if (repairCalc.repairHistory.isEmpty()) {
            throw new IllegalStateException("Nie zainicjalizowałeś historii napraw!");
        } else {
            return repairCalc;
        }
    }

}
