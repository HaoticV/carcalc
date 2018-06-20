package pl.wrzosdev;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wrzosdev.model.Cost;
import pl.wrzosdev.model.FuelCost;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class FuelTest {

    public static final float DAILY_FUEL_COST_EXPECTED = (float) (230 + 172 + 237) / (15 + 30 - 4);

    @Before
    public void setUp() throws Exception {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Cost> fuelCosts = new ArrayList<>(Arrays.asList(
                new FuelCost(sdf.parse("04/04/2018"), 243450, 230, 47),
                new FuelCost(sdf.parse("15/04/2018"), 243992, 172, 35),
                new FuelCost(sdf.parse("28/04/2018"), 244747, 237, 46),
                new FuelCost(sdf.parse("15/05/2018"), 245425, 238, 45)));
        Statistics.init(fuelCosts);
    }

    @Test //suma litrów
    public void GivenFuelTankSetWhenLitersSumRequestThenProperSumReturned() {
        Assert.assertEquals("Suma litrów nie jest liczona prawidłowo!",
                173, Statistics.fuelCalc.allLitersSum());
    }

    @Test //dzienny koszt paliwa
    public void GivenFuelTankSetWhenDailyFuelCostIsRequestThenProperCostIsReturned() {
        Assert.assertEquals("Dzienny koszt paliwa jest liczony źle",
                DAILY_FUEL_COST_EXPECTED, Statistics.fuelCalc.dailyCostLiters(), 0.004);
    }

    @Test //największy dystans bez tankowania
    public void GivenFuelTankSetWhenMaxDistanceRequestThenProperSumReturned() {
        Assert.assertEquals("Najdłuższy dystans bez tankowania nie jest liczony prawdiłowo!",
                755, Statistics.fuelCalc.maxDist());
    }

    @Test //spalanie na setkę
    public void GivenFuelTankSetWhenFuelConsumptionPer100RequestThenProperConsumtion() {
        Assert.assertEquals("Spalanie na setkę jest niepoprawne!",
                6.48, Statistics.fuelCalc.burningFor100km(), 0.09);
    }

    @Test //średnie spalanie
    public void GivenFuelTankSetWhenFrequencyTankRequestThenProperConsumtion() {
        Assert.assertEquals("Średnie spalanie jest niepoprawne!",
                10, Statistics.fuelCalc.frequencyTank(), 0.01);
    }

    @Test //największa ilość dni bez tankowania
    public void GivenFuelTankSetWhenMaxDaysWithoutTankRequestThenProperConsumtion() {
        Assert.assertEquals("Najwieksza ilosc dni bez tankowania jest niepoprawna!",
                17, Statistics.fuelCalc.maxDaysWithoutTank(), 0.01);
    }

    @Test //koszt jednego kilometra
    public void GivenFuelTankSetWhenCost1KMRequestThenProperConsumtion() {
        Assert.assertEquals("Spalanie na 1km jest niepoprawne!",
                0.44, Statistics.fuelCalc.cost1KM(), 0.01);
    }
    @Test //koszt jednego klitra
    public void GivenFuelTankSetWhenCost1liteRequestThenProperCostReturned() {
        Assert.assertEquals("Koszt jednego litra nie jest liczony prawidłowo",
                5.07, Statistics.fuelCalc.cost1Liter(), 0.001);
    }

}