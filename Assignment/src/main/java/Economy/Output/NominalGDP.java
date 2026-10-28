package Economy.Output;

import Economy.Output.Enums.OutputMetricName;


/**
 * NominalGDP takes the variables double(consumption, investment, govermentPurchases, netExports).
 * getName() returns the enum OutputMetricName.NOMINAL_GDP. getUnit() return "currency".
 * evaluate() returns the calculated Nominal gdp.
 * */


public class NominalGDP extends OutputMetric{

    private final double consumption;
    private final double investment;
    private final double governmentPurchases;
    private final double netExports;

    public NominalGDP(double consumption, double investment, double governmentPurchases, double netExports){
        this.consumption = consumption;
        this.investment = investment;
        this.governmentPurchases = governmentPurchases;
        this.netExports = netExports;
    }


    public String getName(){
        return OutputMetricName.NOMINAL_GDP.getName();
    };

    public String getUnit(){
        return "currency";
    };

    public double evaluate(){
        return consumption + investment + governmentPurchases + netExports;
    };
}
