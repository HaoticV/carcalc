package pl.wrzosdev.costs.fuel;

import pl.wrzosdev.model.FuelCost;

import java.util.ArrayList;

class FuelUtils {
    static int allCostUsedFuel(ArrayList<FuelCost> fuelHistory) {
        int sumOfRefills = 0;
        for (int i = 0; i <= fuelHistory.size() - 1 - 1; i++) {
            sumOfRefills += fuelHistory.get(i).cost;
        }
        return sumOfRefills;
    }
    static int litersSum(ArrayList<FuelCost> fuelHistory) {
        int sumOfLiters = 0;
        for (int i = 0; i <= fuelHistory.size() - 1; i++) {
            sumOfLiters += fuelHistory.get(i).liters;
        }
        return sumOfLiters;
    }
}
