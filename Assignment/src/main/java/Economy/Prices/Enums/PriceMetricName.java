package Economy.Prices.Enums;

public enum PriceMetricName {
    CONSUMER_PRICE_INDEX("Consumer Price Index", "The cost of a fixed basket relative to its cost in the base period."),
    INFLATION_RATE("Inflation rate", "The percentage change in the price index from the previous period.");

    private final String name;
    private final String description;

    PriceMetricName(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
