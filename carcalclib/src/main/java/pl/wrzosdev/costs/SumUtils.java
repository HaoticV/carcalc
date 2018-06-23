package pl.wrzosdev.costs;

import pl.wrzosdev.model.FuelCost;
import pl.wrzosdev.model.costs.Cost;

import java.util.ArrayList;
//todo specyficzne utilsy do dedykowanych klas utilsow
public class SumUtils {

    //? extends cost pozwala przyjac kolekcji dowolna klase dziedziczaca po cost
    public static int allCosts(ArrayList<? extends Cost> costsHistory) {
        int sumOfRefills = 0;
        for (int i = 0; i <= costsHistory.size() - 1; i++) {
            sumOfRefills += costsHistory.get(i).cost;
        }
        return sumOfRefills;
    }

    public static int allLitersSum(ArrayList<FuelCost> fuelHistory) {
        Integer liters = 0;
        for (FuelCost fuel : fuelHistory) {
            liters += fuel.liters;
        }
        return liters;
    }

    public static int allMileageSum(ArrayList<FuelCost> fuelHistory) {
        return fuelHistory.get(fuelHistory.size() - 1).mileage - fuelHistory.get(0).mileage;
    }


    public static long allTimeSum(ArrayList<? extends Cost> fuelHistory) {
        return fuelHistory.get(fuelHistory.size() - 1).date.getTime() - fuelHistory.get(0).date.getTime();
    }

    public static int allCostRepairs(ArrayList<? extends Cost> repairCostHistory) {
        int sum = 0;
        for (int i = 0; i <= repairCostHistory.size() - 1; i++) {
            sum += repairCostHistory.get(i).cost;
        }
        return sum;
    }
}
