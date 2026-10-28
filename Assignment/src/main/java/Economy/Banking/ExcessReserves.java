package Economy.Banking;

import Economy.Banking.Enums.BankingMetricName;

import javax.swing.plaf.basic.BasicLookAndFeel;

public class ExcessReserves extends BankingMetric{

    private final double actualReserves;
    private final double requiredReserves;

    public ExcessReserves(double actualReserves, double requiredReserves){
        this.actualReserves = actualReserves;
        this.requiredReserves = requiredReserves;
    }
    public String getName(){
        return BankingMetricName.EXCESS_RESERVES.getName();
    };
    public String getUnit(){
        return "currency";
    };
    public double evaluate(){
        return actualReserves-requiredReserves;
    };
}
