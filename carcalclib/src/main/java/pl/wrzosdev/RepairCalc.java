package pl.wrzosdev;

import pl.wrzosdev.model.Repairs;

import java.util.ArrayList;

public class RepairCalc {
    ArrayList<Repairs> repairsHistory = new ArrayList<>();

    public int sumCostOfAllRepairs() {
            int sum = 0;
            for(int i = 0; i<= repairsHistory.size()-1; i++) {
                sum += repairsHistory.get(i).cost;
            }
            return sum;
        }
}
