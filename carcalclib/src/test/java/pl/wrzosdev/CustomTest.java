package pl.wrzosdev;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.wrzosdev.model.CustomCost;
import pl.wrzosdev.model.costs.Cost;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(Parameterized.class)
public class CustomTest {
    public static final String SINGLE_ARRAY_NAME = "SINGLE_ARRAY";
    public static final String DOUBLE_ARRAY_NAME = "DOUBLE_ARRAY";
    public static final String TRIPLE_ARRAY_NAME = "TRIPLE_ARRAY";
    public static final String LONG_ARRAY_NAME = "LONG_ARRAY";
    public static List<Cost> SINGLE_ARRAY;
    public static List<Cost> DOUBLE_ARRAY;
    public static List<Cost> TRIPLE_ARRAY;
    public static List<Cost> LONG_ARRAY;

    static {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            SINGLE_ARRAY = Arrays.asList(
                    new CustomCost(sdf.parse("01/06/2018"), 242950, 800, "OC"));
            DOUBLE_ARRAY = Arrays.asList(
                    new CustomCost(sdf.parse("01/06/2018"), 242950, 800, "OC"),
                    new CustomCost(sdf.parse("15/06/2018"), 243392, 300, "Polerka"));
            TRIPLE_ARRAY = Arrays.asList(
                    new CustomCost(sdf.parse("01/06/2018"), 242950, 800, "OC"),
                    new CustomCost(sdf.parse("15/06/2018"), 243392, 300, "Polerka"),
                    new CustomCost(sdf.parse("30/06/2018"), 243392, 50, "Myjnia"));
            LONG_ARRAY = Arrays.asList(
                    new CustomCost(sdf.parse("01/06/2018"), 242950, 800, "OC"),
                    new CustomCost(sdf.parse("15/06/2018"), 243392, 300, "Polerka"),
                    new CustomCost(sdf.parse("30/06/2018"), 243392, 50, "Myjnia"),
                    new CustomCost(sdf.parse("09/06/2018"), 245925, 700, "Szofer"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Parameterized.Parameter(0)
    public List<Cost> inputCollection;
    @Parameterized.Parameter(1)
    public String expectedValue;
    private Map<String, Double> resultsDouble;
    private Map<String, Integer> resultsInt;

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

    @Test //koszt wszystkich napraw
    public void GivenCustomSetWhenSumAllCustomCostRequestedThenProperSumReturned() {
        resultsInt.put(SINGLE_ARRAY_NAME, 800);
        resultsInt.put(DOUBLE_ARRAY_NAME, 1100);
        resultsInt.put(TRIPLE_ARRAY_NAME, 1150);
        resultsInt.put(LONG_ARRAY_NAME, 1850);
        Statistics.init(inputCollection);
        Assert.assertEquals("Suma kosztów wszystkich napraw nie jest liczona prawidłowo!",
                resultsInt.get(expectedValue), Statistics.getCustomCalc().sumAllCustomCost());
    }
}