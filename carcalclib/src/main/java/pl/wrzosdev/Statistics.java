package pl.wrzosdev;

import pl.wrzosdev.model.Cost;
import pl.wrzosdev.model.CustomCost;
import pl.wrzosdev.model.FuelCost;
import pl.wrzosdev.model.RepairCost;

import java.util.ArrayList;

public class Statistics {
    static FuelCalc fuelCalc;
    static RepairCalc repairCalc;
    static CustomCalc customCalc;

    public static void init(ArrayList<Cost> costsHistory) {
        fuelCalc = new FuelCalc();
        repairCalc = new RepairCalc();
        customCalc = new CustomCalc();
        for (Cost cost : costsHistory) {
            if (cost instanceof FuelCost) fuelCalc.fuelHistory.add((FuelCost) cost);
            if (cost instanceof RepairCost) repairCalc.repairHistory.add((RepairCost) cost);
        }
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
