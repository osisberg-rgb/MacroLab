package Economy.Enums;

public enum ComponentCategory {

    MACRO_METRIC("Macro metric", "A calculated measure used to describe the macroeconomy."),
    FISCAL_POLICY("Fiscal policy", "A government tax or spending item with a budget effect.");

    private final String name;
    private final String description;

    ComponentCategory(String name, String description){
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
