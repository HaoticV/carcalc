package pl.wrzosdev;

import pl.wrzosdev.model.FuelTank;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class Calculator {
    /**
     * Zestawienie tankowań posortowane datami
     */
    ArrayList<FuelTank> fuelHistory = new ArrayList<>();
    // TODO: 29.05.2018 czy sumUtils ma być delegatem ze stanem klasy czy tylko statyczną narzędziówką na życzenie?


    public float burningFor100km() {
        Integer fuelSum = SumUtils.allLitersSum(fuelHistory);
        Integer allMileageDist;
        Float burning;
        allMileageDist = SumUtils.allMileageSum(fuelHistory);
        burning = (float) (fuelSum * 100) / allMileageDist;
        return burning;
    }

    public float dailyCostLiters() {
        long timeDiff = TimeUnit.MILLISECONDS.toDays(SumUtils.allTimeSum(fuelHistory)) + 1;
        int costSum = SumUtils.allCostSum(fuelHistory);
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
}


