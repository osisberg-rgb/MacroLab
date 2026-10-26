package Economy.Banking.Enums;

public enum BankingMetricName {
    MONEY_MULTIPLIER("Money multiplier", "The reciprocal of the required reserve ratio."),
    REQUIRED_RESERVES("Required reserves", "Deposits multiplied by the required reserve ratio."),
    EXCESS_RESERVES("Excess reserves", "Actual reserves minus required reserves.");

    private final String name;
    private final String description;

    BankingMetricName(String name, String description){
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
