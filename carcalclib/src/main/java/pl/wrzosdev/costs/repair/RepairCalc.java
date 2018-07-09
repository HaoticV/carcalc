package pl.wrzosdev.costs.repair;

import pl.wrzosdev.Statistics;
import pl.wrzosdev.costs.SumUtils;
import pl.wrzosdev.model.RepairCost;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RepairCalc {
    public List<RepairCost> repairHistory = new ArrayList<>();

    /**
     * @return Suma kosztów napraw
     */
    public int sumCostOfAllRepairs() {
        return SumUtils.allCosts(repairHistory);
    }

    /**
     * @return Jak często naprawiasz
     */
    public float frequencyRepair() {
        long days = TimeUnit.MILLISECONDS.toDays(repairHistory.get(repairHistory.size() - 1).date.getTime() - repairHistory.get(0).date.getTime());
        return (float) days / repairHistory.size();
    }

    /**
     * @return średni koszt naprawy
     */
    public float avgCostRepair(){
        return (float) SumUtils.allCosts(repairHistory)/repairHistory.size();
    }

    /**
     * @return mieszięczny koszt napraw
     */
    public float monthlyCostOfRepairs() {
        long timeDiff = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - Statistics.getCostCalc().firstCostDate().getTime());
        return (float) SumUtils.allCosts(repairHistory) * 30 / timeDiff;
    }
}
