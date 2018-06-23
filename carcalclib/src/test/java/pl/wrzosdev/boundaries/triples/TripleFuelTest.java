package pl.wrzosdev.boundaries.triples;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wrzosdev.Statistics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class TripleFuelTest {

    @Before
    public void setUp() throws Exception {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Statistics.init(new ArrayList<>((Arrays.asList(

        ))));
    }

    @Test
    public void GivenRepairCostsWhenCommonRepairCostRequestedThenProperCostReturned(){
        Assert.assertTrue(false);
        // TODO: 23.06.2018 dopisać jakieś testy
    }
}