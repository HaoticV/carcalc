package pl.wrzosdev;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wrzosdev.model.FuelTank;
import pl.wrzosdev.model.Repair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomTest {
    @Before
    public void setUp() throws Exception {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<FuelTank> fuelTanks = new ArrayList<>(Arrays.asList(
                new FuelTank(sdf.parse("04/04/2018"), 243450, 230, 47),
                new FuelTank(sdf.parse("15/04/2018"), 243992, 172, 35),
                new FuelTank(sdf.parse("28/04/2018"), 244747, 237, 46),
                new FuelTank(sdf.parse("15/05/2018"), 245425, 238, 45)));
        ArrayList<Repair> repairs = new ArrayList<>(Arrays.asList(
                new Repair(sdf.parse("01/03/2018"), 242950, 250, "Klimatyzacja", "Gębka", 378232891),
                new Repair(sdf.parse("05/04/2018"), 243392, 1000, "Wymiana zawieszenia", "Franciszków", 781049813),
                new Repair(sdf.parse("05/04/2018"), 243392, 100, "Wydech", "Franciszków", 781049813),
                new Repair(sdf.parse("30/05/2018"), 245925, 960, "Łącznik stabilizatora, szyba, tarcze, klocki", "Bosh", 672123322)));
        Statistics.initFuelAndRepair(fuelTanks, repairs);
    }

    @Test //łączny koszt napraw i paliwa
    public void GivenRepairAndFuelSetWhenFuelAndRepairCostsRequestedThenProperSumReturned() {
        Assert.assertEquals("Łączny koszt napraw i paliwa jest liczony nieprawidłowo",
                3187, Statistics.customCalc.FuelAndRepairCosts());
    }

    @Test //miesięczny koszt napraw
    public void GivenRepairSetWhenMonthCostRepairsRequestedThenProperSumReturned() {
        Assert.assertEquals("Łączny koszt napraw jest liczony nieprawidłowo",
                778.65, Statistics.customCalc.monthCostRepairs(), 0.1);
    }
}