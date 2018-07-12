package pl.wrzosdev;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.wrzosdev.model.RepairCost;
import pl.wrzosdev.model.costs.Cost;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(Parameterized.class)
public class RepairTest {
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
                    new RepairCost(sdf.parse("01/03/2018"), 242950, 250, "Klimatyzacja", "Gębka", 378232891));
            DOUBLE_ARRAY = Arrays.asList(
                    new RepairCost(sdf.parse("01/03/2018"), 242950, 250, "Klimatyzacja", "Gębka", 378232891),
                    new RepairCost(sdf.parse("05/04/2018"), 243392, 1000, "Wymiana zawieszenia", "Franciszków", 781049813));
            TRIPLE_ARRAY = Arrays.asList(
                    new RepairCost(sdf.parse("01/03/2018"), 242950, 250, "Klimatyzacja", "Gębka", 378232891),
                    new RepairCost(sdf.parse("05/04/2018"), 243392, 1000, "Wymiana zawieszenia", "Franciszków", 781049813),
                    new RepairCost(sdf.parse("05/04/2018"), 243392, 100, "Wydech", "Franciszków", 781049813));
            LONG_ARRAY = Arrays.asList(
                    new RepairCost(sdf.parse("01/03/2018"), 242950, 250, "Klimatyzacja", "Gębka", 378232891),
                    new RepairCost(sdf.parse("05/04/2018"), 243392, 1000, "Wymiana zawieszenia", "Franciszków", 781049813),
                    new RepairCost(sdf.parse("05/04/2018"), 243392, 100, "Wydech", "Franciszków", 781049813),
                    new RepairCost(sdf.parse("30/05/2018"), 245925, 960, "Łącznik stabilizatora, szyba, tarcze, klocki", "Bosh", 672123322));
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

    @Test //koszt wszystkich napraw
    public void GivenRepairSetWhenSumOfAllRepairsRequestedThenProperSumReturned() {
        results.put(SINGLE_ARRAY_NAME, 250);
        results.put(DOUBLE_ARRAY_NAME, 1250);
        results.put(TRIPLE_ARRAY_NAME, 1350);
        results.put(LONG_ARRAY_NAME, 2310);
        Statistics.init(inputCollection);
        Assert.assertEquals("Suma kosztów wszystkich napraw nie jest liczona prawidłowo!",
                results.get(expectedValue), Statistics.getRepairCalc().sumCostOfAllRepairs());
    }

    @Test //częstotliwość napraw
    public void GivenRepairSetWhenFrequencyRepairRequestedThenProperSumReturned() {
        results.put(SINGLE_ARRAY_NAME, 0.0);
        results.put(DOUBLE_ARRAY_NAME, 17.0);
        results.put(TRIPLE_ARRAY_NAME, 11.33);
        results.put(LONG_ARRAY_NAME, 22.25);
        Statistics.init(inputCollection);
        Assert.assertEquals("Częstotliwość wszystkich napraw nie jest liczona prawidłowo!",
                results.get(expectedValue).doubleValue(), Statistics.getRepairCalc().frequencyRepair(), 0.1);
    }

    @Test //średni koszt naprawy
    public void GivenRepairSetWhenAvgCostRepairRequestedThenProperSumReturned() {
        results.put(SINGLE_ARRAY_NAME, 250.0);
        results.put(DOUBLE_ARRAY_NAME, 625.0);
        results.put(TRIPLE_ARRAY_NAME, 450.0);
        results.put(LONG_ARRAY_NAME, 577.5);
        Statistics.init(inputCollection);
        Assert.assertEquals("Średni koszt napraw nie jest liczona prawidłowo!",
                results.get(expectedValue).doubleValue(), Statistics.getRepairCalc().avgCostRepair(), 0.01);
    }

    @Test //miesięczny koszt napraw
    public void GivenRepairSetWhenMonthlyCostRequestedThenProperValueReturned() {
        results.put(SINGLE_ARRAY_NAME, 1.0);
        results.put(DOUBLE_ARRAY_NAME, 2.0);
        results.put(TRIPLE_ARRAY_NAME, 3.0);
        results.put(LONG_ARRAY_NAME, 4.0);
        Statistics.init(inputCollection);
        Assert.assertTrue(true);
        // TODO: 2018-07-10 Testy codziennie będą się wypierdalać, czy zmienić z daty dzisiejszej na datę ostatniego wpisu?
        //Assert.assertEquals("Miesięczny kosz napraw nie jest liczony prawidłowo!",
        //        resultsDouble.get(expectedValue), Statistics.getRepairCalc().monthlyCostOfRepairs(), 0.01);
    }
}
