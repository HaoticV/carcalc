package pl.wrzosdev;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wrzosdev.model.FuelCost;
import pl.wrzosdev.model.RepairCost;
import pl.wrzosdev.model.costs.Cost;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomTest {
    @Before
    public void setUp() throws Exception {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Cost> fuelCosts = new ArrayList<>(Arrays.asList(
                new FuelCost(sdf.parse("04/04/2018"), 243450, 230, 47),
                new FuelCost(sdf.parse("15/04/2018"), 243992, 172, 35),
                new FuelCost(sdf.parse("28/04/2018"), 244747, 237, 46),
                new FuelCost(sdf.parse("15/05/2018"), 245425, 238, 45)));
        ArrayList<Cost> repairCosts = new ArrayList<>(Arrays.asList(
                new RepairCost(sdf.parse("01/03/2018"), 242950, 250, "Klimatyzacja", "Gębka", 378232891),
                new RepairCost(sdf.parse("05/04/2018"), 243392, 1000, "Wymiana zawieszenia", "Franciszków", 781049813),
                new RepairCost(sdf.parse("05/04/2018"), 243392, 100, "Wydech", "Franciszków", 781049813),
                new RepairCost(sdf.parse("30/05/2018"), 245925, 960, "Łącznik stabilizatora, szyba, tarcze, klocki", "Bosh", 672123322)));
        ArrayList<Cost> allcosts = new ArrayList<>();
        allcosts.addAll(fuelCosts);
        allcosts.addAll(repairCosts);
        Statistics.init(allcosts);
    }

    @Test //łączny koszt napraw i paliwa
    public void GivenCostSetWhenCostsRequestedThenProperSumReturned() {
        Assert.assertEquals("Łączny koszt calkowity jest liczony nieprawidłowo",
                3187, Statistics.costCalc.allCost());
    }

   /* @Test //miesięczny koszt napraw
    public void GivenRepairSetWhenMonthCostRepairsRequestedThenProperSumReturned() {
        Assert.assertEquals("Łączny koszt napraw jest liczony nieprawidłowo",
                778.65, Statistics.costCalc.monthCostRepairs(), 0.1);
    }*/
}