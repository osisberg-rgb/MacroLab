package Economy.Prices;

import Economy.Metrics.Enums.MetricGroup;
import Economy.Metrics.MacroMetric;

public abstract class PriceMetric extends MacroMetric {

    public final MetricGroup getGroup(){
        return MetricGroup.PRICES;
    };
    public abstract String getName();
    public abstract String getUnit();
    public abstract double evaluate();

}
