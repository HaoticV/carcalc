package pl.wrzosdev;

import pl.wrzosdev.model.FuelTank;

import java.util.ArrayList;

class SumUtils {

    static int allCostSum(ArrayList<FuelTank> fuelHistory) {
        int sumOfRefills = 0;
        // TODO: 29.05.2018 ustalić czy liczymy ostatnie tankowanie jako koniec okresu czy tez jako koszta?
        for (int i = 0; i <= fuelHistory.size() - 1 - 1; i++) {
            sumOfRefills += fuelHistory.get(i).cost;
        }
        return sumOfRefills;
    }

    static int allLitersSum(ArrayList<FuelTank> fuelHistory) {
        Integer liters = 0;
        for (FuelTank fuel : fuelHistory) {
            liters += fuel.liters;
        }
        return liters;
    }

    static int allMileageSum(ArrayList<FuelTank> fuelHistory) {
        return fuelHistory.get(fuelHistory.size() - 1).mileage - fuelHistory.get(0).mileage;
    }


    static long allTimeSum(ArrayList<FuelTank> fuelHistory) {
        return fuelHistory.get(fuelHistory.size() - 1).date.getTime() - fuelHistory.get(0).date.getTime();
    }
}
