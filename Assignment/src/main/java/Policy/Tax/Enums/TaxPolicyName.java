package Policy.Tax.Enums;

public enum TaxPolicyName {

    FLAT_INCOME_TAX_REVENUE("Flat income-tax revenue", "Revenue from applying one tax rate to taxable income."),
    SALES_TAX_REVENUE("Sales-tax revenue", "Revenue from applying a tax rate to taxable spending.");

    private final String name;
    private final String description;

    TaxPolicyName(String name, String description){
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
