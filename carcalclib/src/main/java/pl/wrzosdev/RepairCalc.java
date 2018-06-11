package pl.wrzosdev;

import pl.wrzosdev.model.Repair;

import java.util.ArrayList;

public class RepairCalc {
    ArrayList<Repair> repairHistory = new ArrayList<>();

    public int sumCostOfAllRepairs() {
            return SumUtils.allCostRepairs(repairHistory);
        }
}
