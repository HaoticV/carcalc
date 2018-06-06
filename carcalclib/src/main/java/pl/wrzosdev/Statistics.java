package pl.wrzosdev;

import pl.wrzosdev.model.FuelTank;
import pl.wrzosdev.model.Repair;

import java.util.ArrayList;

public class Statistics {
    static FuelCalc fuelCalc = new FuelCalc();
    static RepairCalc repairCalc = new RepairCalc();

    public static void initFuel(ArrayList<FuelTank> fuelHistory) { Statistics.fuelCalc.fuelHistory = fuelHistory; }
    public static void initRepairs(ArrayList<Repair> repairHistory) { Statistics.repairCalc.repairHistory = repairHistory; }

    public static FuelCalc getFuelCalc() {
        if (fuelCalc.fuelHistory.isEmpty()) {
            throw new IllegalStateException("Nie zainicjalizowałeś historii tankowania!");
        } else {
            return fuelCalc;
        }
    }
    public static RepairCalc getRepairCalc() {
        if (repairCalc.repairHistory.isEmpty()) {
            throw new IllegalStateException("Nie zainicjalizowałeś historii tankowania!");
        } else {
            return repairCalc;
        }
    }
}
