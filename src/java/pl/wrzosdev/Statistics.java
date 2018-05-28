package pl.wrzosdev;

import pl.wrzosdev.model.FuelTank;

import java.util.ArrayList;

public class Statistics {
    public static Calculator calculator = new Calculator();

    public static void main(String[] args){
        System.out.println(" Test");
    }

    public static void init(ArrayList<FuelTank> fuelHistory){
        Statistics.calculator.fuelHistory = fuelHistory;
    }
}
