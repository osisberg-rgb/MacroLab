package Economy.Banking;

import Economy.Banking.Enums.BankingMetricName;

public class RequiredReserves extends BankingMetric{

    private final double deposits;
    private final double reserveRequirementPercent;

    public RequiredReserves(double deposits, double reserveRequirementPercent){
        this.deposits = deposits;
        this.reserveRequirementPercent = reserveRequirementPercent;
    }

    public String getName(){
        return BankingMetricName.REQUIRED_RESERVES.getName();
    };
    public String getUnit(){
        return "currency";
    };
    public double evaluate(){
        return reserveRequirementPercent/deposits;
    };
}
