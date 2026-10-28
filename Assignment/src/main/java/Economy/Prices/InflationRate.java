package Economy.Prices;

import Economy.Prices.Enums.PriceMetricName;

public class InflationRate extends PriceMetric{

    private final double currentCpi;
    private final double previousCpi;

    public InflationRate(double currentCpi, double previousCpi){
        this.currentCpi = currentCpi;
        this.previousCpi = previousCpi;
    }


    public String getName(){
        return PriceMetricName.INFLATION_RATE.getName();
    };
    public String getUnit(){
        return "percent";
    };

    public double evaluate(){
        return ((currentCpi-previousCpi)/previousCpi) *100 ;
    };
}
