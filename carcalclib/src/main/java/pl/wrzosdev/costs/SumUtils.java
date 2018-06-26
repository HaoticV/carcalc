package pl.wrzosdev.costs;

import pl.wrzosdev.model.FuelCost;
import pl.wrzosdev.model.costs.Cost;

import java.util.ArrayList;
//todo specyficzne utilsy do dedykowanych klas utilsow
public class SumUtils {

    //? extends cost pozwala przyjac kolekcji dowolna klase dziedziczaca po cost
    public static int allCosts(ArrayList<? extends Cost> costsHistory) {
        int sumOfCosts = 0;
        for (int i = 0; i <= costsHistory.size() - 1; i++) {
            sumOfCosts += costsHistory.get(i).cost;
        }
        return sumOfCosts;
    }

    public static int allMileageSum(ArrayList<? extends Cost> dataSet) {
        return dataSet.get(dataSet.size() - 1).mileage - dataSet.get(0).mileage;
    }


    public static long allTimeSum(ArrayList<? extends Cost> dataSet) {
        return dataSet.get(dataSet.size() - 1).date.getTime() - dataSet.get(0).date.getTime();
    }


}
