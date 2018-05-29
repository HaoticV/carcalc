package pl.wrzosdev;

import pl.wrzosdev.model.FuelTank;

import java.util.ArrayList;

public class Statistics {
    static Calculator calculator = new Calculator();

    public static void init(ArrayList<FuelTank> fuelHistory) {
        Statistics.calculator.fuelHistory = fuelHistory;
    }

    public static Calculator getCalculator() {
        if (calculator.fuelHistory.isEmpty()) {
            throw new IllegalStateException("Nie zainicjalizowałeś historii tankowania!");
        } else {
            return calculator;
        }
    }
}
