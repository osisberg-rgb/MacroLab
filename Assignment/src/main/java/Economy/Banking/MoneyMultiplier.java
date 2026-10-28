package Economy.Banking;

import Economy.Banking.Enums.BankingMetricName;

public class MoneyMultiplier extends BankingMetric{

    private final double reserveRequirementPercent;


    public MoneyMultiplier(double reserveRequirementPercent){
        this.reserveRequirementPercent = reserveRequirementPercent;
    }

    public String getName(){
        return BankingMetricName.MONEY_MULTIPLIER.getName();
    };
    public String getUnit(){
        return "Multiplier";
    };
    public double evaluate(){
        return 1/reserveRequirementPercent;
    };
}
