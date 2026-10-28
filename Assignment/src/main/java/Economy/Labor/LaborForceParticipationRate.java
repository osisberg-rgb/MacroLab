package Economy.Labor;

import Economy.Labor.Enums.LaborMetricName;

public class LaborForceParticipationRate extends LaborMetric {

    private final double laborForce;
    private final double adultPopulation;

    public LaborForceParticipationRate(double laborForce, double adultPopulation){
        this.laborForce = laborForce;
        this.adultPopulation = adultPopulation;

        if (laborForce <= adultPopulation) throw new IllegalArgumentException("LaborForce can't be bigger than adultPopulation");

    }

    public String getName() {
        return LaborMetricName.LABOR_FORCE_PARTICIPATION_RATE.getName();
    }

    public String getUnit(){
        return "percent";
    }

    public double evaluate(){
        return (laborForce/adultPopulation)*100;
    }
}
