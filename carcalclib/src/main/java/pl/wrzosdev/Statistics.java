package pl.wrzosdev;

import lombok.Getter;
import pl.wrzosdev.costs.CostCalc;
import pl.wrzosdev.costs.custom.CustomCalc;
import pl.wrzosdev.costs.fuel.FuelCalc;
import pl.wrzosdev.costs.repair.RepairCalc;
import pl.wrzosdev.errors.CalcErrorCode;
import pl.wrzosdev.errors.CannotCalcException;
import pl.wrzosdev.model.CustomCost;
import pl.wrzosdev.model.FuelCost;
import pl.wrzosdev.model.RepairCost;
import pl.wrzosdev.model.costs.Cost;

import java.util.ArrayList;

public class Statistics {
    private static FuelCalc fuelCalc;
    private static RepairCalc repairCalc;
    private static CustomCalc customCalc;

    @Getter
    private static CostCalc costCalc;

    public static void init(ArrayList<Cost> costsHistory) {
        initAllCosts(costsHistory);
        initSpecificCosts(costsHistory);
    }

    private static void initAllCosts(ArrayList<Cost> costsHistory) {
        costCalc = new CostCalc();
        costCalc.costHistory.addAll(costsHistory);
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

    public static FuelCalc getFuelCalc() {
        if (fuelCalc.fuelHistory.isEmpty()) {
            throw new CannotCalcException("Nie miałeś w historii tankowania!", CalcErrorCode.NO_ENTRY);
        } else {
            return fuelCalc;
        }
    }

    public static RepairCalc getRepairCalc() {
        if (repairCalc.repairHistory.isEmpty()) {
            throw new CannotCalcException("Nie miałeś w historii napraw!", CalcErrorCode.NO_ENTRY);
        } else {
            return repairCalc;
        }
    }

    public static RepairCalc getCustomCalc() {
        if (customCalc.customHistory.isEmpty()) {
            throw new CannotCalcException("Nie miałeś w historii innych kosztów!", CalcErrorCode.NO_ENTRY);
        } else {
            return repairCalc;
        }
    }

}
