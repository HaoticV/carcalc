package pl.wrzosdev;

import pl.wrzosdev.model.FuelTank;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class FuelCalc {
    /**
     * Zestawienie tankowań posortowane datami
     */
    ArrayList<FuelTank> fuelHistory = new ArrayList<>();
    // TODO: 29.05.2018 czy sumUtils ma być delegatem ze stanem klasy czy tylko statyczną narzędziówką na życzenie?


    public float burningFor100km() {
        Integer fuelSum = SumUtils.allLitersSum(fuelHistory) - fuelHistory.get(fuelHistory.size()-1).liters;
        Integer allMileageDist;
        allMileageDist = SumUtils.allMileageSum(fuelHistory);
        return (float) (fuelSum * 100) / allMileageDist; //procentowa proporcja;
    }

    /**
     *
     * @return Dzienny koszt paliwa
     */
    public float dailyCostLiters() {
        long timeDiff = TimeUnit.MILLISECONDS.toDays(SumUtils.allTimeSum(fuelHistory));
        int costSum = SumUtils.allCostUsedFuel(fuelHistory);
        return (float) costSum / timeDiff;
    }


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
        return SumUtils.allLitersSum(fuelHistory);
    }

    public long frequencyTank(){
        long avgtank = SumUtils.allTimeSum(fuelHistory)/(fuelHistory.size());
        return TimeUnit.MILLISECONDS.toDays(avgtank);
    }

    public long maxDaysWithoutTank() {
        long max = 0;
        for (int i = fuelHistory.size() - 1; i > 0 ; i--) {
            if (max < (fuelHistory.get(i).date.getTime()-fuelHistory.get(i-1).date.getTime()))
                max = (fuelHistory.get(i).date.getTime()-fuelHistory.get(i-1).date.getTime());
        }
        return TimeUnit.MILLISECONDS.toDays(max);
    }

    public float cost1KM() {
        int sumcost = 0;
        for (FuelTank aFuelHistory : fuelHistory) {
            sumcost += aFuelHistory.cost;
        }
        return (float)sumcost/(fuelHistory.get(fuelHistory.size() - 1).mileage - fuelHistory.get(0).mileage);
    }

    public double cost1Liter() {
        return (double)SumUtils.allCostFuel(fuelHistory) / SumUtils.allLitersSum(fuelHistory);
    }
}


