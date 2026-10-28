package Economy.Output;

import Economy.Output.Enums.OutputMetricName;

/**
 * GDPDeflator extends OutputMetric, and takes the parameter double (nominalGDP, realGDP). getName() returns the enum value,
 * getUnit() return "index". evaluate() returns (nominalGDP/realGDP)*100
 */

public class GDPDeflator extends OutputMetric{
    private final double nominalGDP;
    private final double realGDP;

    public GDPDeflator(double nominalGDP, double realGDP){
        this.nominalGDP = nominalGDP;
        this.realGDP = realGDP;
    }

    public String getName(){
        return OutputMetricName.GDP_DEFLATOR.getName();
    }

    public String getUnit(){
        return "index";
    }

    public double evaluate(){
        return (nominalGDP/realGDP)*100;
    }

}
