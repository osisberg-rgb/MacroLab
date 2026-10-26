package Economy.Metrics;

import Economy.EconomicComponent;
import Economy.Enums.ComponentCategory;
import Economy.Metrics.Enums.MetricGroup;

/*
    This is an abstract class that extends EconomicComponent,
    and always return Macro_metric as ComponentCategory.
 */


public abstract class MacroMetric extends EconomicComponent {


    public abstract MetricGroup getGroup();
    public abstract String getName();
    public abstract String getUnit();
    public abstract double evaluate();

    public final ComponentCategory getComponentCategory(){
        return ComponentCategory.MACRO_METRIC;
    };



}
