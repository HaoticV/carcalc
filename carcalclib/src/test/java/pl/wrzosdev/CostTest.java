package pl.wrzosdev;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.wrzosdev.model.CustomCost;
import pl.wrzosdev.model.FuelCost;
import pl.wrzosdev.model.RepairCost;
import pl.wrzosdev.model.costs.Cost;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(Parameterized.class)
public class CostTest {
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
                    new FuelCost(sdf.parse("04/04/2018"), 243450, 230, 47));
            DOUBLE_ARRAY = Arrays.asList(
                    new FuelCost(sdf.parse("04/04/2018"), 243450, 230, 47),
                    new RepairCost(sdf.parse("30/05/2018"), 245925, 960, "Łącznik stabilizatora, szyba, tarcze, klocki", "Bosh", 672123322));
            TRIPLE_ARRAY = Arrays.asList(
                    new FuelCost(sdf.parse("04/04/2018"), 243450, 230, 47),
                    new CustomCost(sdf.parse("15/04/2018"), 243992, 172, "Dla tego buca z ubezpieczalni"),
                    new RepairCost(sdf.parse("01/03/2018"), 242950, 250, "Klimatyzacja", "Gębka", 378232891));
            LONG_ARRAY = Arrays.asList(
                    new FuelCost(sdf.parse("04/04/2018"), 243450, 230, 47),
                    new FuelCost(sdf.parse("15/04/2018"), 243992, 172, 35),
                    new CustomCost(sdf.parse("28/04/2018"), 244747, 237, "Na myjnię i autodetaling"),
                    new CustomCost(sdf.parse("15/05/2018"), 245425, 238, "Perfumy do samochodu"),
                    new RepairCost(sdf.parse("01/03/2018"), 242950, 250, "Klimatyzacja", "Gębka", 378232891),
                    new RepairCost(sdf.parse("05/04/2018"), 243392, 1000, "Wymiana zawieszenia", "Franciszków", 781049813),
                    new RepairCost(sdf.parse("05/04/2018"), 243392, 100, "Wydech", "Franciszków", 781049813),
                    new RepairCost(sdf.parse("30/05/2018"), 245925, 960, "Łącznik stabilizatora, szyba, tarcze, klocki", "Bosh", 672123322));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Kolekcja zadanych danych, dla których są spięte spodziewane wyniki.
     * Są to pary takie że:
     * {pierwszy, drugi}
     * pierwszy element to kolekcja kosztów
     * drugi to spodziewany wynik obliczenia
     * Odpalane przed każdym uruchomieniem zestawu testów.
     *
     * @return Kolekcja parametryzacji testów
     */

    @Parameterized.Parameter(0)
    public List<Cost> inputCollection;
    @Parameterized.Parameter(1)
    public String expectedValue;
    private Map<String, Number> resultsInt;

    /**
     * Uruchamiane przed każdym odpaleniem pojedynczego testu(czyli wiele razy na odpalenie zestawu testów)
     *
     * @throws Exception
     */

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
        resultsInt = new HashMap<>();
    }

    @Test //łączny koszt napraw i paliwa
    public void GivenCostSetWhenCostsRequestedThenProperSumReturned() {
        resultsInt.put(SINGLE_ARRAY_NAME, 47);
        resultsInt.put(DOUBLE_ARRAY_NAME, 82);
        resultsInt.put(TRIPLE_ARRAY_NAME, 128);
        resultsInt.put(LONG_ARRAY_NAME, 173);
        Statistics.init(inputCollection);
        Assert.assertEquals("Łączny koszt calkowity jest liczony nieprawidłowo",
                resultsInt.get(expectedValue), Statistics.getCostCalc().allCost());
    }
}