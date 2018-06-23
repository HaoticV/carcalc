package pl.wrzosdev;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wrzosdev.model.RepairCost;
import pl.wrzosdev.model.costs.Cost;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class RepairCostTest {
    @Before
    public void setUp() throws Exception {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Cost> repairCosts = new ArrayList<>(Arrays.asList(
                new RepairCost(sdf.parse("01/03/2018"), 242950, 250, "Klimatyzacja", "Gębka", 378232891),
                new RepairCost(sdf.parse("05/04/2018"), 243392, 1000, "Wymiana zawieszenia", "Franciszków", 781049813),
                new RepairCost(sdf.parse("05/04/2018"), 243392, 100, "Wydech", "Franciszków", 781049813),
                new RepairCost(sdf.parse("30/05/2018"), 245925, 960, "Łącznik stabilizatora, szyba, tarcze, klocki", "Bosh", 672123322)));
        Statistics.init(repairCosts);
    }

    @Test //koszt wszystkich napraw
    public void GivenRepairSetWhenSumOfAllRepairsRequestedThenProperSumReturned() {
        Assert.assertEquals("Suma kosztów wszystkich napraw nie jest liczona prawidłowo!",
                2310, Statistics.repairCalc.sumCostOfAllRepairs());
    }

    @Test //częstotliwość napraw
    public void GivenRepairSetWhenFrequencyRepairRequestedThenProperSumReturned() {
        Assert.assertEquals("Częstotliwość wszystkich napraw nie jes liczona prawidłowo!",
                22.25, Statistics.repairCalc.frequencyRepair(), 0.1);
    }
}
