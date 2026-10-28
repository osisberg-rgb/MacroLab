package Economy.Labor;

import Economy.Labor.Enums.LaborMetricName;


public class UnemploymentRate extends LaborMetric{
    private final double unemployed;
    private final double laborForce;

    public UnemploymentRate(double unemployed, double laborForce){
        this.unemployed = unemployed;
        this.laborForce = laborForce;

        if(unemployed<=laborForce) throw new IllegalArgumentException(
                "unemployed can't be smaller or equal laborForce");

    }

    public String getName(){
        return LaborMetricName.UNEMPLOYMENT_RATE.getName();
    }

    public String getUnit(){
        return "percent";
    }

    public double evaluate(){
        return (unemployed/laborForce)*100;
    }

}
