package pl.wrzosdev.costs;

import pl.wrzosdev.model.costs.Cost;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SumUtils {

    //? extends cost pozwala przyjac kolekcji dowolna klase dziedziczaca po cost
    public static int allCosts(List<? extends Cost> dataSet) {
        int sumOfCosts = 0;
        for (int i = 0; i <= dataSet.size() - 1; i++) {
            sumOfCosts += dataSet.get(i).cost;
        }
        return sumOfCosts;
    }

    public static int allMileageSum(List<? extends Cost> dataSet) {
        return dataSet.get(dataSet.size() - 1).mileage - dataSet.get(0).mileage;
    }


    public static long allTimeSum(List<? extends Cost> dataSet) {
        return dataSet.get(dataSet.size() - 1).date.getTime() - dataSet.get(0).date.getTime();
    }

    /**
     * Zwraca najstarszą datę(pierwszy wydatek) lub aktualną datę, jeśli nie ma wpisów
     * @param costHistory historia wszystkich kosztów
     * @return Data najstarszego wpisu
     */
    public static Date firstCostDate(List<Cost> costHistory) {
        Optional<Cost> optionalCost = costHistory.stream().min(Comparator.comparing(cost -> cost.date));
        long currentDateTime = System.currentTimeMillis();
        Date currentDate = new Date(currentDateTime);
        if (optionalCost.isPresent())
            return optionalCost.get().date;
        else
            return currentDate;
    }

}
