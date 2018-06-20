package pl.wrzosdev;

import pl.wrzosdev.model.Cost;
import pl.wrzosdev.model.FuelCost;
import pl.wrzosdev.model.RepairCost;

import java.util.ArrayList;

class SumUtils {

    static int allCostUsedFuel(ArrayList<FuelCost> fuelHistory) {
        int sumOfRefills = 0;
        for (int i = 0; i <= fuelHistory.size() - 1 - 1; i++) {
            sumOfRefills += fuelHistory.get(i).cost;
        }
        return sumOfRefills;
    }

    //? extends cost pozwala przyjac kolekcji dowolna klase dziedziczaca po cost
    static int allCostFuel(ArrayList<? extends Cost> fuelHistory) {
        int sumOfRefills = 0;
        for (int i = 0; i <= fuelHistory.size() - 1; i++) {
            sumOfRefills += fuelHistory.get(i).cost;
        }
        return sumOfRefills;
    }

    static int allLitersSum(ArrayList<FuelCost> fuelHistory) {
        Integer liters = 0;
        for (FuelCost fuel : fuelHistory) {
            liters += fuel.liters;
        }
        return liters;
    }

    static int allMileageSum(ArrayList<FuelCost> fuelHistory) {
        return fuelHistory.get(fuelHistory.size() - 1).mileage - fuelHistory.get(0).mileage;
    }


    static long allTimeSum(ArrayList<? extends Cost> fuelHistory) {
        return fuelHistory.get(fuelHistory.size() - 1).date.getTime() - fuelHistory.get(0).date.getTime();
    }

    static int allCostRepairs(ArrayList<Cost> repairCostHistory) {
        int sum = 0;
        for (int i = 0; i <= repairCostHistory.size() - 1; i++) {
            sum += repairCostHistory.get(i).cost;
        }
        return sum;
    }

}
