package pl.wrzosdev.costs.fuel;

import pl.wrzosdev.costs.SumUtils;
import pl.wrzosdev.model.FuelCost;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FuelCalc {
    /**
     * Zestawienie tankowań posortowane datami
     */
    public List<FuelCost> fuelHistory = new ArrayList<>();

    public float burningFor100km() {
        Integer fuelSum = FuelUtils.litersSum(fuelHistory)-fuelHistory.get(fuelHistory.size()-1).liters;
        Integer allMileageDist = SumUtils.allMileageSum(fuelHistory);
        return (float) (fuelSum * 100) / allMileageDist; //procentowa proporcja;
    }

    /**
     * @return Dzienny koszt paliwa
     */
    public float dailyCostLiters() {
        long timeDiff = TimeUnit.MILLISECONDS.toDays(SumUtils.allTimeSum(fuelHistory));
        int costSum = FuelUtils.allCostUsedFuel(fuelHistory);
        return (float) costSum / timeDiff;
    }

    /**
     * @return Maksymalny dystans bez tankowania
     */
    public int maxDist() {
        Integer max = 0;
        Integer distance;
        for (int i = 0; i < fuelHistory.size() - 1; i++) {
            distance = fuelHistory.get(i + 1).mileage - fuelHistory.get(i).mileage;
            if (max < distance) max = distance;
        }
        return max;
    }

    public int allLitersSum() {
        return FuelUtils.litersSum(fuelHistory);
    }
    /**
     * @return Jak często tankujesz
     */
    public long frequencyTank() {
        long avgtank = SumUtils.allTimeSum(fuelHistory) / (fuelHistory.size());
        return TimeUnit.MILLISECONDS.toDays(avgtank);
    }
    /**
     * @return Najdłuższy czas bez tankowania
     */
    public long maxDaysWithoutTank() {
        long max = 0;
        for (int i = fuelHistory.size() - 1; i > 0; i--) {
            if (max < (fuelHistory.get(i).date.getTime() - fuelHistory.get(i - 1).date.getTime()))
                max = (fuelHistory.get(i).date.getTime() - fuelHistory.get(i - 1).date.getTime());
        }
        return TimeUnit.MILLISECONDS.toDays(max);
    }

    public float cost1KM() {
        int sumcost = 0;
        for (FuelCost aFuelHistory : fuelHistory) {
            sumcost += aFuelHistory.cost;
        }
        return (float) sumcost / (fuelHistory.get(fuelHistory.size() - 1).mileage - fuelHistory.get(0).mileage);
    }

    public double cost1Liter() {
        return (double) SumUtils.allCosts(fuelHistory)/FuelUtils.litersSum(fuelHistory);
    }
}


