package Economy.Metrics;

import Economy.EconomicComponent;
import Economy.Enums.ComponentCategory;
import Economy.Metrics.Enums.MetricGroup;

public abstract class MacroMetric extends EconomicComponent {



    public abstract MetricGroup getGroup();

    public final ComponentCategory getComponentCategory(){
        return ComponentCategory.MACRO_METRIC;
    };

    public abstract String getName();
    public abstract String getUnit();
    public abstract double evaluate();
    public abstract ComponentCategory getComponentCategory();



}
