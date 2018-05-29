package pl.wrzosdev;

import pl.wrzosdev.model.FuelTank;

import java.util.ArrayList;

public class Calculator {
    ArrayList<FuelTank> fuelHistory;

    public int litersSum() {
        Integer liters = 0;
        for (FuelTank fuel :
                fuelHistory) {
            liters += fuel.liters;
        }
        return liters;
    }


    public int maxDist() {
        Integer max = 0;
        Integer km = 0;
        for (int i = 0; i <fuelHistory.size()-1 ; i++) {
            km = fuelHistory.get(i+1).mileage - fuelHistory.get(i).mileage;
            if(max<km) max=km;
        }
        return max;
    }
}