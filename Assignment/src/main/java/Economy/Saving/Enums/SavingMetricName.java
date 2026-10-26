package Economy.Saving.Enums;

public enum SavingMetricName {

    PRIVATE_SAVING("Private saving", "Household income remaining after taxes and consumption."),
    PUBLIC_SAVING("Public saving", "Net tax revenue remaining after government purchases."),
    NATIONAL_SAVING("National saving", "The sum of private saving and public saving.");

    private final String name;
    private final String description;

    SavingMetricName(String name, String description){
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
