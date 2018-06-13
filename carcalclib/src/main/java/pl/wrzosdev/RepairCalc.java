package pl.wrzosdev;

import pl.wrzosdev.model.Repair;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RepairCalc {
    ArrayList<Repair> repairHistory = new ArrayList<>();

    public int sumCostOfAllRepairs() {
        return SumUtils.allCostRepairs(repairHistory);
    }

    public float frequencyRepair() {
        long days = TimeUnit.MILLISECONDS.toDays(repairHistory.get(repairHistory.size() - 1).date.getTime() - repairHistory.get(0).date.getTime());
        return (float) days / repairHistory.size();
    }
}
