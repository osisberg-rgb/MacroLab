package Policy.Spending.Enums;

public enum SpendingPolicyName {

    GOVERNMENT_PURCHASES("Government purchases", "Government spending on newly produced goods and services."),
    TRANSFER_PAYMENTS("Transfer payments", "Government payments that are not purchases of newly produced output.");

    private final String name;
    private final String description;

    SpendingPolicyName(String name, String description){
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
