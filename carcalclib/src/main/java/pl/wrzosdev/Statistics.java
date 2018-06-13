package pl.wrzosdev;

import pl.wrzosdev.model.FuelTank;
import pl.wrzosdev.model.Repair;

import java.util.ArrayList;

public class Statistics {
    static FuelCalc fuelCalc = new FuelCalc();
    static RepairCalc repairCalc = new RepairCalc();
    static CustomCalc customCalc = new CustomCalc();

    public static void initFuel(ArrayList<FuelTank> fuelHistory) { Statistics.fuelCalc.fuelHistory = fuelHistory; }
    public static void initRepairs(ArrayList<Repair> repairHistory) { Statistics.repairCalc.repairHistory = repairHistory; }

    public static void initFuelAndRepair(ArrayList<FuelTank> fuelHistory, ArrayList<Repair> repairHistory) {
        Statistics.customCalc.fuelHistory = fuelHistory;
        Statistics.customCalc.repairHistory = repairHistory;
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

    public static CustomCalc getCustomCalc() {
        if (customCalc.fuelHistory.isEmpty() || customCalc.repairHistory.isEmpty())
            throw new IllegalStateException("Nie zainicjowalizowałeś historii tankowania lub napraw!");
        else
            return customCalc;

    }

}
