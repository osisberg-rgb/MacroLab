package Economy.Output;

import Economy.Output.Enums.OutputMetricName;


/**
 * ClosedEconomyGDP inherit is functions from nominalGDP. It takes the same parameters, but sets the nx to 0.0,
 * because it is not used in closedEconomy. ClosedEconomyGDP, override getName() to return = .CLOSED_ECONOMY_GDP
 */

public class ClosedEconomyGDP extends NominalGDP{


    public ClosedEconomyGDP(double consumption, double investment, double governmentPurchases){
        super(consumption, investment, governmentPurchases, 0.0);

    }

    @Override
    public String getName(){
        return OutputMetricName.CLOSED_ECONOMY_GDP.getName();
    }

}
