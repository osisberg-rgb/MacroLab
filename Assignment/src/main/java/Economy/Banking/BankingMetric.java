package Economy.Banking;

import Economy.Banking.Enums.BankingMetricName;
import Economy.Enums.ComponentCategory;
import Economy.Metrics.Enums.MetricGroup;
import Economy.Metrics.MacroMetric;

/*
    This is an abstract class, that extends MacroMetric.
    Makes it final returning MetricGroup.BANKING.
 */

public abstract class BankingMetric extends MacroMetric {

    public final MetricGroup getGroup(){
        return MetricGroup.BANKING;
    };

    public abstract String getName();
    public abstract String getUnit();
    public abstract double evaluate();


}
