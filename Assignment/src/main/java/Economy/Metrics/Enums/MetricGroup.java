package Economy.Metrics.Enums;

public enum MetricGroup {
    OUTPUT("Output", "Measures production and real economic activity."),
    PRICES("Prices", "Measures the price level and changes in prices."),
    LABOR("Labor", "Measures employment and participation in the labor market."),
    SAVING("Saving", "Measures private, public, and national saving."),
    BANKING("Banking", "Measures reserve requirements and deposit expansion.");

    private final String name;
    private final String description;

    MetricGroup(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
