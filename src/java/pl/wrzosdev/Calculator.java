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
}
