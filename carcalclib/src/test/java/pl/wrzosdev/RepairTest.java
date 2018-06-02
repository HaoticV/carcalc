package pl.wrzosdev;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wrzosdev.model.Repairs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class RepairTest {
    @Before
    public void setUp() throws Exception {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Repairs> repairs = new ArrayList<>(Arrays.asList(
                new Repairs(sdf.parse("15/03/2018"), 242950, 250, "Klimatyzacja", "Gębka", 378232891),
                new Repairs(sdf.parse("05/04/2018"), 243392, 1000, "Wymiana zawieszenia", "Franciszków", 781049813),
                new Repairs(sdf.parse("05/04/2018"), 243392, 100, "Wydech", "Franciszków", 781049813),
                new Repairs(sdf.parse("30/05/2018"), 245925, 960, "Łącznik stabilizatora, szyba, tarcze, klocki", "Bosh", 672123322)));
        Statistics.initRepairs(repairs);
    }

    @Test //koszt wszystkich napraw
    public void GivenRepairSetWhenSumOfAllRepairsRequestedThenProperSumReturned() {
        Assert.assertEquals("Suma kosztów wszystkich napraw nie jest liczona prawidłowo!",
                2310, Statistics.repairCalc.sumCostOfAllRepairs());
    }
}
