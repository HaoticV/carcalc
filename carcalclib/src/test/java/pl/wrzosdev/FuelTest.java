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
                results.get(expectedValue), (Statistics.getFuelCalc().allLitersSum()));
    }

    @Test //dzienny koszt paliwa
    public void GivenFuelTankSetWhenDailyFuelCostIsRequestThenProperCostIsReturned() {
        results.put(SINGLE_ARRAY_NAME, Double.NaN);
        results.put(DOUBLE_ARRAY_NAME, 20.90);
        results.put(TRIPLE_ARRAY_NAME, 16.75);
        results.put(LONG_ARRAY_NAME, 15.58);
        Statistics.init(inputCollection);
        Assert.assertEquals("Dzienny koszt paliwa jest liczony źle",
                results.get(expectedValue).doubleValue(), Statistics.getFuelCalc().dailyCostLiters(), 0.01);
    }

    @Test //największy dystans bez tankowania
    public void GivenFuelTankSetWhenMaxDistanceRequestThenProperSumReturned() {
        results.put(SINGLE_ARRAY_NAME, 0);
        results.put(DOUBLE_ARRAY_NAME, 542);
        results.put(TRIPLE_ARRAY_NAME, 755);
        results.put(LONG_ARRAY_NAME, 755);
        Statistics.init(inputCollection);
        Assert.assertEquals("Najdłuższy dystans bez tankowania nie jest liczony prawdiłowo!",
                results.get(expectedValue), Statistics.getFuelCalc().maxDist());
    }

    @Test //spalanie na setkę
    public void GivenFuelTankSetWhenFuelConsumptionPer100RequestThenProperConsumtion() {
        results.put(SINGLE_ARRAY_NAME, Double.NaN);
        results.put(DOUBLE_ARRAY_NAME, 8.67);
        results.put(TRIPLE_ARRAY_NAME, 6.32);
        results.put(LONG_ARRAY_NAME, 6.48);
        Statistics.init(inputCollection);
        Assert.assertEquals("Spalanie na setkę jest niepoprawne!",
                results.get(expectedValue).doubleValue(), Statistics.getFuelCalc().burningFor100km(), 0.01);
    }

    @Test //częstość tankowań
    public void GivenFuelTankSetWhenFrequencyTankRequestThenProperConsumtion() {
        results.put(SINGLE_ARRAY_NAME, 0L);
        results.put(DOUBLE_ARRAY_NAME, 5L);
        results.put(TRIPLE_ARRAY_NAME, 8L);
        results.put(LONG_ARRAY_NAME, 10L);
        Statistics.init(inputCollection);
        Assert.assertEquals("Częstośc nakowań nie jest liczona prawidłowo!",
                results.get(expectedValue), Statistics.getFuelCalc().frequencyTank());
    }

    @Test //największa ilość dni bez tankowania
    public void GivenFuelTankSetWhenMaxDaysWithoutTankRequestThenProperConsumtion() {
        results.put(SINGLE_ARRAY_NAME, 0L);
        results.put(DOUBLE_ARRAY_NAME, 11L);
        results.put(TRIPLE_ARRAY_NAME, 13L);
        results.put(LONG_ARRAY_NAME, 17L);
        Statistics.init(inputCollection);
        Assert.assertEquals("Najwieksza ilosc dni bez tankowania jest niepoprawna!",
                results.get(expectedValue), Statistics.getFuelCalc().maxDaysWithoutTank());
    }

    @Test //koszt jednego kilometra
    public void GivenFuelTankSetWhenCost1KMRequestThenProperConsumtion() {
        results.put(SINGLE_ARRAY_NAME, Infinity);
        results.put(DOUBLE_ARRAY_NAME, 0.74);
        results.put(TRIPLE_ARRAY_NAME, 0.49);
        results.put(LONG_ARRAY_NAME, 0.44);
        Statistics.init(inputCollection);
        Assert.assertEquals("Spalanie na 1km jest niepoprawne!",
                results.get(expectedValue).doubleValue(), Statistics.getFuelCalc().cost1KM(), 0.01);
    }

    @Test //koszt jednego litra
    public void GivenFuelTankSetWhenCost1liteRequestThenProperCostReturned() {
        results.put(SINGLE_ARRAY_NAME, 4.89);
        results.put(DOUBLE_ARRAY_NAME, 4.90);
        results.put(TRIPLE_ARRAY_NAME, 4.99);
        results.put(LONG_ARRAY_NAME, 5.06);
        Statistics.init(inputCollection);
        Assert.assertEquals("Koszt jednego litra nie jest liczony prawidłowo",
                results.get(expectedValue).doubleValue(), Statistics.getFuelCalc().cost1Liter(), 0.01);
    }

}