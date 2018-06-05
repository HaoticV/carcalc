package pl.wrzosdev;

import pl.wrzosdev.model.Repair;

import java.util.ArrayList;

public class RepairCalc {
    ArrayList<Repair> repairHistory = new ArrayList<>();

    public int sumCostOfAllRepairs() {
            int sum = 0;
            for(int i = 0; i<= repairHistory.size()-1; i++) {
                sum += repairHistory.get(i).cost;
            }
            return sum;
        }
}
