package pl.wrzosdev.costs.custom;

import pl.wrzosdev.costs.SumUtils;
import pl.wrzosdev.model.CustomCost;

import java.util.ArrayList;
import java.util.List;

public class CustomCalc {
    public List<CustomCost> customHistory = new ArrayList<>();

    public Integer sumAllCustomCost() {
        return SumUtils.allCosts(customHistory);
    }
}
