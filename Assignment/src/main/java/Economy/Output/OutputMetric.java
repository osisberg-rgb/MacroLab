package Economy.Output;

import Economy.Metrics.Enums.MetricGroup;
import Economy.Metrics.MacroMetric;

public abstract class OutputMetric extends MacroMetric {

    public final MetricGroup getGroup(){
        return MetricGroup.OUTPUT;
    };

    public abstract String getName();
    public abstract String getUnit();
    public abstract double evaluate();

}
