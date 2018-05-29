package pl.wrzosdev;

import pl.wrzosdev.model.FuelTank;

import java.util.ArrayList;

public class Calculator {
    ArrayList<FuelTank> fuelHistory;

    public int litersSum() {
        Integer liters = 0;
        for (FuelTank fuel : fuelHistory) {
            liters += fuel.liters;
        }
        return liters;
    }


    public float spalanieNa100() {
        Integer fuel = 0;
        Integer dist = 0;
        Float spalanie = 0f;
        for (int i = 0; i < fuelHistory.size(); i++) {
            fuel += fuelHistory.get(i).liters;
        }
        dist = fuelHistory.get(fuelHistory.size() - 1).mileage - fuelHistory.get(0).mileage;
        spalanie = (float) (fuel * 100) / dist;
        return spalanie;
    }

    public float dailyCostLiters() {
        float dailyCostFuel = 0;
        long timedif = fuelHistory.get(fuelHistory.size() - 1).date.getTime() - fuelHistory.get(0).date.getTime();
        timedif = timedif / (1000 * 60 * 60 * 24) + 1;
        int sumOfRefils = 0;
        for (int i = 0; i <= fuelHistory.size() - 2; i++) {
            sumOfRefils += fuelHistory.get(i).cost;
        }
        dailyCostFuel = (float) sumOfRefils / timedif;
        return dailyCostFuel;
    }


    public int maxDist() {
        Integer max = 0;
        Integer km = 0;
        for (int i = 0; i < fuelHistory.size() - 1; i++) {
            km = fuelHistory.get(i + 1).mileage - fuelHistory.get(i).mileage;
            if (max < km) max = km;
        }
        return max;
    }


}


