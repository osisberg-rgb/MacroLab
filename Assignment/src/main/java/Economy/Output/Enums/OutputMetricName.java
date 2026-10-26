package Economy.Output.Enums;

public enum OutputMetricName {

    NOMINAL_GDP("Nomial GDP", "The market value of final goods and services measured at current prices."),
    CLOSED_ECONOMY_GDP("Closed-economy GDP","Nominal GDP for an economy with zero net exports."),
    REAL_GDP("Real GDP", "Output measured after adjusting nominal GDP for the price level."),
    GDP_DEFLATOR("GDP Deflator", "A price index comparing nominal GDP with real GDP."),
    REAL_GDP_PER_CAPITA("Real GDP per capita", "Real GDP divided by population.");

    private final String name;
    private final String description;

    OutputMetricName(String name, String description){
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
