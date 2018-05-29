package pl.wrzosdev;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wrzosdev.model.FuelTank;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class StatisticsTest {

    @Before
    public void setUp() throws Exception {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<FuelTank> fuelTanks = new ArrayList<FuelTank>(Arrays.asList(
                new FuelTank(sdf.parse("04/04/2018"), 243450, 47, 230),
                new FuelTank(sdf.parse("15/04/2018"), 243992, 35, 172),
                new FuelTank(sdf.parse("28/04/2018"), 244747, 46, 237),
                new FuelTank(sdf.parse("15/05/2018"), 245425, 45, 238)));
        Statistics.init(fuelTanks);

    }
    @Test
    public void GivenFuelTankSetWhenLitersSumRequestThenProperSumReturned(){
        Assert.assertEquals("Suma litrów nie jest liczona prawidłowo!",
               173,  Statistics.calculator.litersSum());
    }

    @Test


    public void GivenFuelTankSetWhenDailyFuelCostisReqThenPrV(){
        Assert.assertEquals("Dzienny koszt paliwa jest liczony źle", (float)(230+172+237)/(15+30-4+1), Statistics.calculator.dailyCostLiters(), 0.004
        );
    }


    public void GivenFuelTankSetWhenMaxDistRequestThenProperSumReturned(){
        Assert.assertEquals("Najdłuższy dystans bez tankowania nie jest liczony prawdiłowo!",
                755,  Statistics.calculator.maxDist());
    }

    @Test
    public void GivenFuelTankSetWhenSpalanieNa100RequestThenProperSumReturned(){
        Assert.assertEquals("Spalanie na setkę jest niepoprawne!",
                8.75,  Statistics.calculator.spalanieNa100(), 0.09);
    }




}