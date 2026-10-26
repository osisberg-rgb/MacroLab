package Economy.Labor;

import Economy.Metrics.Enums.MetricGroup;
import Economy.Metrics.MacroMetric;

public abstract class LaborMetric extends MacroMetric {

    public final MetricGroup getGroup(){
        return MetricGroup.LABOR;
    };

    public abstract String getName();
    public abstract String getUnit();
    public abstract double evaluate();

}
