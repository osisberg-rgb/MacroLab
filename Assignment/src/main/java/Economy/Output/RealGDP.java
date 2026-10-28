package Economy.Output;

import Economy.Output.Enums.OutputMetricName;

/**
 * RealGDP extends OutMetric, and takes the parameters double(nominalGDP, gdpDeflator).
 * getName() returns it enums value. getUnit() returns "currency". evaluate() returns (nominal/gdpDeflator)*100
 */

public class RealGDP extends OutputMetric{
    private final double nominalGDP;
    private final double gdpDeflator;

    public RealGDP(double nominalGDP, double gdpDeflator){
        this.nominalGDP = nominalGDP;
        this.gdpDeflator = gdpDeflator;
    }

    public String getName(){
        return OutputMetricName.REAL_GDP.getName();
    };

    public String getUnit(){
        return "currency";
    };

    public double evaluate(){
        return (nominalGDP/gdpDeflator)*100;
    };
}
