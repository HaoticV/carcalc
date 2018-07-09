package pl.wrzosdev;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.wrzosdev.model.FuelCost;
import pl.wrzosdev.model.costs.Cost;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(Parameterized.class)
public class FuelTest {
    public static final float DAILY_FUEL_COST_EXPECTED = (float) (230 + 172 + 237) / (15 + 30 - 4);
    public static final String SINGLE_ARRAY_NAME = "SINGLE_ARRAY";
    public static final String DOUBLE_ARRAY_NAME = "DOUBLE_ARRAY";
    public static final String TRIPLE_ARRAY_NAME = "TRIPLE_ARRAY";
    public static final String LONG_ARRAY_NAME = "LONG_ARRAY";
    public static List<FuelCost> SINGLE_ARRAY;
    public static List<FuelCost> DOUBLE_ARRAY;
    public static List<FuelCost> TRIPLE_ARRAY;
    public static List<FuelCost> LONG_ARRAY;


    static {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            SINGLE_ARRAY = Arrays.asList(
                    new FuelCost(sdf.parse("04/04/2018"), 243450, 230, 47));
            DOUBLE_ARRAY = Arrays.asList(
                    new FuelCost(sdf.parse("04/04/2018"), 243450, 230, 47),
                    new FuelCost(sdf.parse("15/04/2018"), 243992, 172, 35));
            TRIPLE_ARRAY = Arrays.asList(
                    new FuelCost(sdf.parse("04/04/2018"), 243450, 230, 47),
                    new FuelCost(sdf.parse("15/04/2018"), 243992, 172, 35),
                    new FuelCost(sdf.parse("28/04/2018"), 244747, 237, 46));
            LONG_ARRAY = Arrays.asList(
                    new FuelCost(sdf.parse("04/04/2018"), 243450, 230, 47),
                    new FuelCost(sdf.parse("15/04/2018"), 243992, 172, 35),
                    new FuelCost(sdf.parse("28/04/2018"), 244747, 237, 46),
                    new FuelCost(sdf.parse("15/05/2018"), 245425, 238, 45));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Parameterized.Parameter(0)
    public List<Cost> inputCollection;
    @Parameterized.Parameter(1)
    public String expectedValue;
    private Map<String, Number> results;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {(SINGLE_ARRAY), SINGLE_ARRAY_NAME},
                {(DOUBLE_ARRAY), DOUBLE_ARRAY_NAME},
                {(TRIPLE_ARRAY), TRIPLE_ARRAY_NAME},
                {(LONG_ARRAY), LONG_ARRAY_NAME},
        });
    }

    @Before
    public void setUp() {
        results = new HashMap<>();
    }


    @Test //suma litrów
    public void GivenFuelTankSetWhenLitersSumRequestThenProperSumReturned() {
        results.put(SINGLE_ARRAY_NAME, 47);
        results.put(DOUBLE_ARRAY_NAME, 82);
        results.put(TRIPLE_ARRAY_NAME, 128);
        results.put(LONG_ARRAY_NAME, 173);
        Statistics.init(inputCollection);
        Assert.assertEquals("Suma litrów nie jest liczona prawidłowo!",
                results.get(expectedValue), Statistics.getFuelCalc().allLitersSum());
    }

    @Test //dzienny koszt paliwa
    public void GivenFuelTankSetWhenDailyFuelCostIsRequestThenProperCostIsReturned() {
        Assert.assertEquals("Dzienny koszt paliwa jest liczony źle",
                DAILY_FUEL_COST_EXPECTED, Statistics.getFuelCalc().dailyCostLiters(), 0.004);
    }

    @Test //największy dystans bez tankowania
    public void GivenFuelTankSetWhenMaxDistanceRequestThenProperSumReturned() {
        Assert.assertEquals("Najdłuższy dystans bez tankowania nie jest liczony prawdiłowo!",
                755, Statistics.getFuelCalc().maxDist());
    }

    @Test //spalanie na setkę
    public void GivenFuelTankSetWhenFuelConsumptionPer100RequestThenProperConsumtion() {
        Assert.assertEquals("Spalanie na setkę jest niepoprawne!",
                6.48, Statistics.getFuelCalc().burningFor100km(), 0.09);
    }

    @Test //średnie spalanie
    public void GivenFuelTankSetWhenFrequencyTankRequestThenProperConsumtion() {
        Assert.assertEquals("Średnie spalanie jest niepoprawne!",
                10, Statistics.getFuelCalc().frequencyTank(), 0.01);
    }

    @Test //największa ilość dni bez tankowania
    public void GivenFuelTankSetWhenMaxDaysWithoutTankRequestThenProperConsumtion() {
        Assert.assertEquals("Najwieksza ilosc dni bez tankowania jest niepoprawna!",
                17, Statistics.getFuelCalc().maxDaysWithoutTank(), 0.01);
    }

    @Test //koszt jednego kilometra
    public void GivenFuelTankSetWhenCost1KMRequestThenProperConsumtion() {
        Assert.assertEquals("Spalanie na 1km jest niepoprawne!",
                0.44, Statistics.getFuelCalc().cost1KM(), 0.01);
    }

    @Test //koszt jednego litra
    public void GivenFuelTankSetWhenCost1liteRequestThenProperCostReturned() {
        Statistics.init(inputCollection);

        Assert.assertEquals("Koszt jednego litra nie jest liczony prawidłowo",
                5.06, Statistics.getFuelCalc().cost1Liter(), 0.001);
    }

}