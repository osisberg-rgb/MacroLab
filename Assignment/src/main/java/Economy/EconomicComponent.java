package Economy;

import Economy.Enums.ComponentCategory;

public abstract class EconomicComponent {

    public abstract String getName();
    public abstract String getUnit();
    public abstract double evaluate();
    public abstract ComponentCategory getComponentCategory();


}
