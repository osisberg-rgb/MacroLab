package Economy.Saving;

import Economy.Metrics.Enums.MetricGroup;
import Economy.Metrics.MacroMetric;

public abstract class SavingMetric extends MacroMetric {
    public final MetricGroup getGroup(){
        return MetricGroup.SAVING;
    };

    public abstract String getName();
    public abstract String getUnit();
    public abstract double evaluate();

}
