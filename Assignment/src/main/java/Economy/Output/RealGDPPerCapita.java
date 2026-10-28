package Economy.Output;

import Economy.Output.Enums.OutputMetricName;

/**
 * RealGDPPerCapita extends OutputMetric, and takes the parameters double (realGDP, population). getName() returns it enums value,
 * getUnit() returns "currency/person". evaluate() returns realGDP/population.
 */


public class RealGDPPerCapita extends OutputMetric{
    private final double realGDP;
    private final double population;

    public RealGDPPerCapita(double realGDP, double population){
        this.realGDP = realGDP;
        this.population = population;
    }

    public String getName(){
        return OutputMetricName.REAL_GDP_PER_CAPITA.getName();
    }

    public String getUnit(){
        return "currency/person";
    }

    public double evaluate(){
        return realGDP/population;
    }


}
