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

import static jdk.nashorn.internal.objects.Global.Infinity;


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
    private Map<String, Object> resultsInt;
    private Map<String, Double> resultsDouble;

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
        resultsDouble = new HashMap<>();
        resultsInt = new HashMap<>();
    }

    @Test //suma litrów
    public void GivenFuelTankSetWhenLitersSumRequestThenProperSumReturned() {
        resultsInt.put(SINGLE_ARRAY_NAME, 47);
        resultsInt.put(DOUBLE_ARRAY_NAME, 82);
        resultsInt.put(TRIPLE_ARRAY_NAME, 128);
        resultsInt.put(LONG_ARRAY_NAME, 173);
        Statistics.init(inputCollection);
        Assert.assertEquals("Suma litrów nie jest liczona prawidłowo!",
                resultsInt.get(expectedValue), (Statistics.getFuelCalc().allLitersSum()));
    }

    @Test //dzienny koszt paliwa
    public void GivenFuelTankSetWhenDailyFuelCostIsRequestThenProperCostIsReturned() {
        resultsDouble.put(SINGLE_ARRAY_NAME, Double.NaN);
        resultsDouble.put(DOUBLE_ARRAY_NAME, 20.90);
        resultsDouble.put(TRIPLE_ARRAY_NAME, 16.75);
        resultsDouble.put(LONG_ARRAY_NAME, 15.58);
        Statistics.init(inputCollection);
        Assert.assertEquals("Dzienny koszt paliwa jest liczony źle",
                resultsDouble.get(expectedValue), Statistics.getFuelCalc().dailyCostLiters(), 0.01);
    }

    @Test //największy dystans bez tankowania
    public void GivenFuelTankSetWhenMaxDistanceRequestThenProperSumReturned() {
        resultsInt.put(SINGLE_ARRAY_NAME, 0);
        resultsInt.put(DOUBLE_ARRAY_NAME, 542);
        resultsInt.put(TRIPLE_ARRAY_NAME, 755);
        resultsInt.put(LONG_ARRAY_NAME, 755);
        Statistics.init(inputCollection);
        Assert.assertEquals("Najdłuższy dystans bez tankowania nie jest liczony prawdiłowo!",
                resultsInt.get(expectedValue), Statistics.getFuelCalc().maxDist());
    }

    @Test //spalanie na setkę
    public void GivenFuelTankSetWhenFuelConsumptionPer100RequestThenProperConsumtion() {
        resultsDouble.put(SINGLE_ARRAY_NAME, Double.NaN);
        resultsDouble.put(DOUBLE_ARRAY_NAME, 8.67);
        resultsDouble.put(TRIPLE_ARRAY_NAME, 6.32);
        resultsDouble.put(LONG_ARRAY_NAME, 6.48);
        Statistics.init(inputCollection);
        Assert.assertEquals("Spalanie na setkę jest niepoprawne!",
                resultsDouble.get(expectedValue), Statistics.getFuelCalc().burningFor100km(), 0.01);
    }

    @Test //częstość tankowań
    public void GivenFuelTankSetWhenFrequencyTankRequestThenProperConsumtion() {
        resultsInt.put(SINGLE_ARRAY_NAME, 0L);
        resultsInt.put(DOUBLE_ARRAY_NAME, 5L);
        resultsInt.put(TRIPLE_ARRAY_NAME, 8L);
        resultsInt.put(LONG_ARRAY_NAME, 10L);
        Statistics.init(inputCollection);
        Assert.assertEquals("Częstośc nakowań nie jest liczona prawidłowo!",
                resultsInt.get(expectedValue), Statistics.getFuelCalc().frequencyTank());
    }

    @Test //największa ilość dni bez tankowania
    public void GivenFuelTankSetWhenMaxDaysWithoutTankRequestThenProperConsumtion() {
        resultsInt.put(SINGLE_ARRAY_NAME, 0L);
        resultsInt.put(DOUBLE_ARRAY_NAME, 11L);
        resultsInt.put(TRIPLE_ARRAY_NAME, 13L);
        resultsInt.put(LONG_ARRAY_NAME, 17L);
        Statistics.init(inputCollection);
        Assert.assertEquals("Najwieksza ilosc dni bez tankowania jest niepoprawna!",
                resultsInt.get(expectedValue), Statistics.getFuelCalc().maxDaysWithoutTank());
    }

    @Test //koszt jednego kilometra
    public void GivenFuelTankSetWhenCost1KMRequestThenProperConsumtion() {
        resultsDouble.put(SINGLE_ARRAY_NAME, Infinity);
        resultsDouble.put(DOUBLE_ARRAY_NAME, 0.74);
        resultsDouble.put(TRIPLE_ARRAY_NAME, 0.49);
        resultsDouble.put(LONG_ARRAY_NAME, 0.44);
        Statistics.init(inputCollection);
        Assert.assertEquals("Spalanie na 1km jest niepoprawne!",
                resultsDouble.get(expectedValue), Statistics.getFuelCalc().cost1KM(), 0.01);
    }

    @Test //koszt jednego litra
    public void GivenFuelTankSetWhenCost1liteRequestThenProperCostReturned() {
        resultsDouble.put(SINGLE_ARRAY_NAME, 4.89);
        resultsDouble.put(DOUBLE_ARRAY_NAME, 4.90);
        resultsDouble.put(TRIPLE_ARRAY_NAME, 4.99);
        resultsDouble.put(LONG_ARRAY_NAME, 5.06);
        Statistics.init(inputCollection);
        Assert.assertEquals("Koszt jednego litra nie jest liczony prawidłowo",
                resultsDouble.get(expectedValue), Statistics.getFuelCalc().cost1Liter(), 0.01);
    }

}