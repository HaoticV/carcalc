package pl.wrzosdev.boundaries.singles;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wrzosdev.Statistics;
import pl.wrzosdev.model.FuelCost;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class SingleFuelTest {

    @Before
    public void setUp() throws Exception {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Statistics.init(new ArrayList<>((Arrays.asList(
                new FuelCost(sdf.parse("04/04/2018"), 243450, 230, 47)
        ))));
    }

    @Test
    public void GivenRepairCostsWhenCommonRepairCostRequestedThenProperCostReturned(){
        Assert.assertTrue(false);
        //todo może parametryzowane?
        // TODO: 23.06.2018 dopisać jakieś testy
    }
}