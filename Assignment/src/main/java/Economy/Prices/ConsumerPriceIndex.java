package Economy.Prices;

import Economy.Prices.Enums.PriceMetricName;

public class ConsumerPriceIndex extends PriceMetric{

    private final double currentBasketCost;
    private final double baseBasketCost;

    public ConsumerPriceIndex(double currentBasketCost, double baseBasketCost){
        this.currentBasketCost = currentBasketCost;
        this.baseBasketCost = baseBasketCost;
    }


    public String getName(){
        return PriceMetricName.CONSUMER_PRICE_INDEX.getName();
    };
    public String getUnit(){
        return "index";
    };
    public double evaluate(){
        return (currentBasketCost/baseBasketCost)*100;
    };
}
