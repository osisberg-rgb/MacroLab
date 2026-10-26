package Economy.Labor.Enums;

import Economy.Labor.LaborMetric;

public enum LaborMetricName {
    UNEMPLOYMENT_RATE("Unemployment rate", "The unemployed as a percentage of the labor force."),
    LABOR_FORCE_PARTICIPATION_RATE("Labor-force participation rate", "The labor force as a percentage of the adult population.");

    private final String name;
    private final String description;

    LaborMetricName(String name, String description){
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
