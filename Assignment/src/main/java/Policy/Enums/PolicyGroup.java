package Policy.Enums;

public enum PolicyGroup {

    TAX("Tax", "A fiscal policy item that raises government revenue."),
    SPENDING("Spending", "A fiscal policy item that uses government funds.");

    private final String name;
    private final String description;

    PolicyGroup(String name, String description){
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
