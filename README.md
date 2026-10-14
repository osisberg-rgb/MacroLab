[README.md](https://github.com/user-attachments/files/33444616/README.md)
# CSE 220 Practice Assignment: ECON 201 MacroLab

## Goal

Build a larger Java program that models common ECON 201 macroeconomic measures and fiscal-policy items. The assignment is intentionally bigger than the Vehicle practice assignment: you will create **39 Java source files** under `Economy`, plus use the provided `Main.java` and instructor tests.

This assignment practices:

- abstract classes and abstract methods
- multi-level inheritance with `extends`
- constructor chaining with `super(...)`
- polymorphism through abstract references
- enums with names and descriptions
- method overriding
- `final` methods in intermediate abstract classes
- encapsulation with `private final` fields
- input validation
- reusing inherited behavior
- ECON 201 formulas: GDP, CPI, inflation, labor statistics, saving, bank reserves, and fiscal policy

## Scenario

You are building a small **MacroLab** for an introductory economics course. A user should be able to create different economic measures, calculate their values, classify them by economic topic, and process them through shared abstract references.

Your job is to create all missing classes and enums under `Economy` so that the provided `Main.java` and all instructor tests pass.

---

# Rules

1. Use **Java 24**.
2. Do **not** modify `Assignment/src/test/java/instructor/`.
3. Keep `Main.java` in the **default package**.
4. Create every required class and enum with the exact package capitalization shown below.
5. All concrete classes must be `public`.
6. All required abstract classes must be `public abstract`.
7. Every non-static instance field you create must be `private final`.
8. Do not round inside calculation methods. `Main` handles formatting.
9. Use `double` arithmetic.
10. Invalid constructor inputs must throw `IllegalArgumentException`.

You may write your own tests in:

```text
Assignment/src/test/java/student/
```

---

# Required source layout

```text
Assignment/src/main/java/
├── Main.java                         PROVIDED
└── Economy/
    ├── EconomicComponent.java
    ├── Enums/
    │   └── ComponentCategory.java
    ├── Metrics/
    │   ├── MacroMetric.java
    │   ├── Enums/
    │   │   └── MetricGroup.java
    │   ├── Output/
    │   │   ├── OutputMetric.java
    │   │   ├── NominalGDP.java
    │   │   ├── ClosedEconomyGDP.java
    │   │   ├── RealGDP.java
    │   │   ├── GDPDeflator.java
    │   │   ├── RealGDPPerCapita.java
    │   │   └── Enums/OutputMetricName.java
    │   ├── Prices/
    │   │   ├── PriceMetric.java
    │   │   ├── ConsumerPriceIndex.java
    │   │   ├── InflationRate.java
    │   │   └── Enums/PriceMetricName.java
    │   ├── Labor/
    │   │   ├── LaborMetric.java
    │   │   ├── UnemploymentRate.java
    │   │   ├── LaborForceParticipationRate.java
    │   │   └── Enums/LaborMetricName.java
    │   ├── Saving/
    │   │   ├── SavingMetric.java
    │   │   ├── PrivateSaving.java
    │   │   ├── PublicSaving.java
    │   │   ├── NationalSaving.java
    │   │   └── Enums/SavingMetricName.java
    │   └── Banking/
    │       ├── BankingMetric.java
    │       ├── MoneyMultiplier.java
    │       ├── RequiredReserves.java
    │       ├── ExcessReserves.java
    │       └── Enums/BankingMetricName.java
    └── Policy/
        ├── FiscalPolicy.java
        ├── Enums/
        │   └── PolicyGroup.java
        ├── Tax/
        │   ├── TaxPolicy.java
        │   ├── FlatIncomeTaxRevenue.java
        │   ├── SalesTaxRevenue.java
        │   └── Enums/TaxPolicyName.java
        └── Spending/
            ├── SpendingPolicy.java
            ├── GovernmentPurchases.java
            ├── TransferPayments.java
            └── Enums/SpendingPolicyName.java
```

---

# Part 1 — Root abstract class

## `EconomicComponent`

Package:

```java
Economy
```

Create:

```java
public abstract class EconomicComponent
```

It must declare these methods as `public abstract`:

```java
String getName();
String getUnit();
double evaluate();
ComponentCategory getComponentCategory();
```

### Guidance

This is the common parent of **everything** in the project. Later, both macroeconomic metrics and fiscal-policy items can be placed in an `EconomicComponent[]`.

You may put shared validation helper methods here if you want, but you do not have to.

---

# Part 2 — Macro metric hierarchy

## `MacroMetric`

Package:

```java
Economy.Metrics
```

It extends `EconomicComponent` and declares:

```java
public abstract MetricGroup getGroup();
```

It must implement this method as `public final`:

```java
getComponentCategory()
```

and always return:

```java
ComponentCategory.MACRO_METRIC
```

## Topic-specific abstract classes

Each class extends `MacroMetric` and implements `getGroup()` as `public final`:

| Class | Package | Group returned |
| --- | --- | --- |
| `OutputMetric` | `Economy.Metrics.Output` | `MetricGroup.OUTPUT` |
| `PriceMetric` | `Economy.Metrics.Prices` | `MetricGroup.PRICES` |
| `LaborMetric` | `Economy.Metrics.Labor` | `MetricGroup.LABOR` |
| `SavingMetric` | `Economy.Metrics.Saving` | `MetricGroup.SAVING` |
| `BankingMetric` | `Economy.Metrics.Banking` | `MetricGroup.BANKING` |

This means concrete classes do **not** decide their broad topic themselves; they inherit it.

---

# Part 3 — Output metrics

Package:

```java
Economy.Metrics.Output
```

All calculations return unrounded `double` values.

| Class | Direct parent | Public constructor | Name enum | Unit |
| --- | --- | --- | --- | --- |
| `NominalGDP` | `OutputMetric` | `(double consumption, double investment, double governmentPurchases, double netExports)` | `NOMINAL_GDP` | `currency` |
| `ClosedEconomyGDP` | `NominalGDP` | `(double consumption, double investment, double governmentPurchases)` | `CLOSED_ECONOMY_GDP` | inherited |
| `RealGDP` | `OutputMetric` | `(double nominalGDP, double gdpDeflator)` | `REAL_GDP` | `currency` |
| `GDPDeflator` | `OutputMetric` | `(double nominalGDP, double realGDP)` | `GDP_DEFLATOR` | `index` |
| `RealGDPPerCapita` | `OutputMetric` | `(double realGDP, double population)` | `REAL_GDP_PER_CAPITA` | `currency/person` |

### Important inheritance requirement

`ClosedEconomyGDP` must extend `NominalGDP` and call the parent constructor with:

```text
net exports = 0
```

Reuse the inherited GDP calculation. Do not duplicate the GDP formula in `ClosedEconomyGDP`.

---

# Part 4 — Price metrics

Package:

```java
Economy.Metrics.Prices
```

| Class | Constructor | Name enum | Unit |
| --- | --- | --- | --- |
| `ConsumerPriceIndex` | `(double currentBasketCost, double baseBasketCost)` | `CONSUMER_PRICE_INDEX` | `index` |
| `InflationRate` | `(double currentCpi, double previousCpi)` | `INFLATION_RATE` | `percent` |

---

# Part 5 — Labor metrics

Package:

```java
Economy.Metrics.Labor
```

| Class | Constructor | Name enum | Unit |
| --- | --- | --- | --- |
| `UnemploymentRate` | `(double unemployed, double laborForce)` | `UNEMPLOYMENT_RATE` | `percent` |
| `LaborForceParticipationRate` | `(double laborForce, double adultPopulation)` | `LABOR_FORCE_PARTICIPATION_RATE` | `percent` |

Extra validation:

- `unemployed <= laborForce`
- `laborForce <= adultPopulation`

---

# Part 6 — Saving metrics

Package:

```java
Economy.Metrics.Saving
```

| Class | Constructor | Name enum | Unit |
| --- | --- | --- | --- |
| `PrivateSaving` | `(double income, double taxes, double consumption)` | `PRIVATE_SAVING` | `currency` |
| `PublicSaving` | `(double taxes, double governmentPurchases)` | `PUBLIC_SAVING` | `currency` |
| `NationalSaving` | `(double privateSaving, double publicSaving)` | `NATIONAL_SAVING` | `currency` |

`PrivateSaving` and `PublicSaving` may evaluate to negative numbers.

For `NationalSaving`, the two constructor inputs themselves may also be negative, but they must be finite.

---

# Part 7 — Banking metrics

Package:

```java
Economy.Metrics.Banking
```

| Class | Constructor | Name enum | Unit |
| --- | --- | --- | --- |
| `MoneyMultiplier` | `(double reserveRequirementPercent)` | `MONEY_MULTIPLIER` | `multiplier` |
| `RequiredReserves` | `(double deposits, double reserveRequirementPercent)` | `REQUIRED_RESERVES` | `currency` |
| `ExcessReserves` | `(double actualReserves, double requiredReserves)` | `EXCESS_RESERVES` | `currency` |

Reserve requirement percentages must be greater than `0` and less than or equal to `100`.

`ExcessReserves` may evaluate to a negative number.

---

# Part 8 — Fiscal policy hierarchy

## `FiscalPolicy`

Package:

```java
Economy.Policy
```

It extends `EconomicComponent`, declares:

```java
public abstract PolicyGroup getGroup();
```

and implements `getComponentCategory()` as `public final`, always returning:

```java
ComponentCategory.FISCAL_POLICY
```

## `TaxPolicy`

Package:

```java
Economy.Policy.Tax
```

Extends `FiscalPolicy` and implements `getGroup()` as `public final`, returning:

```java
PolicyGroup.TAX
```

## `SpendingPolicy`

Package:

```java
Economy.Policy.Spending
```

Extends `FiscalPolicy` and implements `getGroup()` as `public final`, returning:

```java
PolicyGroup.SPENDING
```

---

# Part 9 — Concrete fiscal-policy classes

## Tax

| Class | Constructor | Name enum | Unit |
| --- | --- | --- | --- |
| `FlatIncomeTaxRevenue` | `(double taxableIncome, double taxRatePercent)` | `FLAT_INCOME_TAX_REVENUE` | `currency` |
| `SalesTaxRevenue` | `(double taxableSpending, double taxRatePercent)` | `SALES_TAX_REVENUE` | `currency` |

Tax rates must be between `0` and `100`, inclusive.

## Spending

| Class | Constructor | Name enum | Unit |
| --- | --- | --- | --- |
| `GovernmentPurchases` | `(double amount)` | `GOVERNMENT_PURCHASES` | `currency` |
| `TransferPayments` | `(double amount)` | `TRANSFER_PAYMENTS` | `currency` |

Amounts must be nonnegative and finite.

---

# Part 10 — Enum contract

Every enum in this project must:

1. be `public`
2. store a display name and description in `private final String` fields
3. initialize them through the enum constructor
4. provide:

```java
public String getName()
public String getDescription()
```

5. override:

```java
public String toString()
```

so `toString()` returns the display name.

Use the exact constants, names, order, and descriptions below.

## `ComponentCategory`

| Constant | Display name | Description |
| --- | --- | --- |
| `MACRO_METRIC` | `Macro metric` | `A calculated measure used to describe the macroeconomy.` |
| `FISCAL_POLICY` | `Fiscal policy` | `A government tax or spending item with a budget effect.` |

## `MetricGroup`

| Constant | Display name | Description |
| --- | --- | --- |
| `OUTPUT` | `Output` | `Measures production and real economic activity.` |
| `PRICES` | `Prices` | `Measures the price level and changes in prices.` |
| `LABOR` | `Labor` | `Measures employment and participation in the labor market.` |
| `SAVING` | `Saving` | `Measures private, public, and national saving.` |
| `BANKING` | `Banking` | `Measures reserve requirements and deposit expansion.` |

## `PolicyGroup`

| Constant | Display name | Description |
| --- | --- | --- |
| `TAX` | `Tax` | `A fiscal policy item that raises government revenue.` |
| `SPENDING` | `Spending` | `A fiscal policy item that uses government funds.` |

## `OutputMetricName`

| Constant | Display name | Description |
| --- | --- | --- |
| `NOMINAL_GDP` | `Nominal GDP` | `The market value of final goods and services measured at current prices.` |
| `CLOSED_ECONOMY_GDP` | `Closed-economy GDP` | `Nominal GDP for an economy with zero net exports.` |
| `REAL_GDP` | `Real GDP` | `Output measured after adjusting nominal GDP for the price level.` |
| `GDP_DEFLATOR` | `GDP deflator` | `A price index comparing nominal GDP with real GDP.` |
| `REAL_GDP_PER_CAPITA` | `Real GDP per capita` | `Real GDP divided by population.` |

## `PriceMetricName`

| Constant | Display name | Description |
| --- | --- | --- |
| `CONSUMER_PRICE_INDEX` | `Consumer Price Index` | `The cost of a fixed basket relative to its cost in the base period.` |
| `INFLATION_RATE` | `Inflation rate` | `The percentage change in the price index from the previous period.` |

## `LaborMetricName`

| Constant | Display name | Description |
| --- | --- | --- |
| `UNEMPLOYMENT_RATE` | `Unemployment rate` | `The unemployed as a percentage of the labor force.` |
| `LABOR_FORCE_PARTICIPATION_RATE` | `Labor-force participation rate` | `The labor force as a percentage of the adult population.` |

## `SavingMetricName`

| Constant | Display name | Description |
| --- | --- | --- |
| `PRIVATE_SAVING` | `Private saving` | `Household income remaining after taxes and consumption.` |
| `PUBLIC_SAVING` | `Public saving` | `Net tax revenue remaining after government purchases.` |
| `NATIONAL_SAVING` | `National saving` | `The sum of private saving and public saving.` |

## `BankingMetricName`

| Constant | Display name | Description |
| --- | --- | --- |
| `MONEY_MULTIPLIER` | `Money multiplier` | `The reciprocal of the required reserve ratio.` |
| `REQUIRED_RESERVES` | `Required reserves` | `Deposits multiplied by the required reserve ratio.` |
| `EXCESS_RESERVES` | `Excess reserves` | `Actual reserves minus required reserves.` |

## `TaxPolicyName`

| Constant | Display name | Description |
| --- | --- | --- |
| `FLAT_INCOME_TAX_REVENUE` | `Flat income-tax revenue` | `Revenue from applying one tax rate to taxable income.` |
| `SALES_TAX_REVENUE` | `Sales-tax revenue` | `Revenue from applying a tax rate to taxable spending.` |

## `SpendingPolicyName`

| Constant | Display name | Description |
| --- | --- | --- |
| `GOVERNMENT_PURCHASES` | `Government purchases` | `Government spending on newly produced goods and services.` |
| `TRANSFER_PAYMENTS` | `Transfer payments` | `Government payments that are not purchases of newly produced output.` |

---

# Part 11 — Validation rules

Unless a rule says otherwise, all numeric constructor inputs must be finite.

Reject `NaN`, positive infinity, and negative infinity.

### Strictly positive inputs

These must be `> 0`:

- nominal GDP when passed to `RealGDP` or `GDPDeflator`
- real GDP when passed to `GDPDeflator` or `RealGDPPerCapita`
- GDP deflator
- population
- current and base basket costs
- current and previous CPI
- labor force when used as a denominator
- adult population
- reserve requirement percentage

### Nonnegative inputs

These may be `0` but not negative:

- consumption, investment, government purchases used in GDP
- unemployed workers
- income, taxes, consumption used in saving calculations
- deposits
- actual reserves and required reserves
- taxable income and taxable spending
- government-purchase and transfer-payment amounts

### Signed finite inputs

These may be positive, zero, or negative:

- net exports
- private saving passed to `NationalSaving`
- public saving passed to `NationalSaving`

### Bounds

- `unemployed <= laborForce`
- `laborForce <= adultPopulation`
- reserve requirement: `0 < percent <= 100`
- tax rate: `0 <= percent <= 100`
- `NominalGDP.evaluate()` must be positive; reject a constructor combination whose total GDP is not positive

---

# Part 12 — `getName()`, `getUnit()`, and `evaluate()`

Each concrete class must override `getName()` and return the display name from its corresponding enum. Example idea:

```text
return SomeEnum.SOME_CONSTANT.toString();
```

Do not hard-code a second copy of the display text if you can reuse the enum.

Each concrete class returns the exact unit specified in the tables above.

Use `FORMULA_SHEET.md` for the economics formulas.

---

# Part 13 — Main and polymorphism

`Main.java` is provided and should not need changes.

It creates:

- all macro metric types
- all fiscal policy types
- arrays using the abstract types `MacroMetric` and `FiscalPolicy`

The tests also combine different branches through the root type:

```java
EconomicComponent[]
```

That is the main polymorphism goal of the assignment.

---

# Recommended build order

Do not try to make all 39 files at once.

### Stage 1 — Enums

Create:

1. `ComponentCategory`
2. `MetricGroup`
3. `PolicyGroup`
4. all seven name enums

### Stage 2 — Abstract hierarchy

Create:

1. `EconomicComponent`
2. `MacroMetric`
3. the five metric group abstract classes
4. `FiscalPolicy`
5. `TaxPolicy`
6. `SpendingPolicy`

### Stage 3 — Easier concrete classes

Start with:

- `GovernmentPurchases`
- `TransferPayments`
- `PrivateSaving`
- `PublicSaving`
- `NationalSaving`
- `ExcessReserves`

### Stage 4 — Percentage and ratio classes

Then implement:

- CPI
- inflation
- unemployment
- labor-force participation
- tax revenue
- required reserves
- money multiplier

### Stage 5 — GDP classes

Finish with:

- `NominalGDP`
- `ClosedEconomyGDP`
- `RealGDP`
- `GDPDeflator`
- `RealGDPPerCapita`

---

# Guidance without giving away the code

## If you are confused about constructors

Ask yourself:

> What information can be different for each object?

That usually belongs in the constructor and in `private final` fields.

For example, every `MoneyMultiplier` object needs a reserve requirement. Every `GovernmentPurchases` object needs an amount.

## If you are confused about inheritance

Ask:

> Is this class a more specific version of its parent?

Examples:

```text
ClosedEconomyGDP IS A NominalGDP
NominalGDP IS AN OutputMetric
OutputMetric IS A MacroMetric
MacroMetric IS AN EconomicComponent
```

## If you are confused about `super(...)`

`ClosedEconomyGDP` should not calculate GDP again. It should provide the three values it receives plus a zero net-export value to the `NominalGDP` constructor.

## If you are confused about final methods

A price metric should not be able to decide that it belongs to the Labor group. Therefore `PriceMetric.getGroup()` is `final`.

Likewise, a macro metric should not be able to claim it is a fiscal policy item.

## If a test fails

Read the **test method name first**. The tests are deliberately named to tell you what contract is failing.

Examples:

```text
closedEconomyGdpReusesNominalGdp
laborMetricsRejectImpossiblePopulationRelationships
allEnumsStoreMetadata
componentsDispatchThroughRootReferences
```

---

# Running the tests in IntelliJ

You do not need the `mvn` command in Terminal.

1. Open the `Assignment` folder in IntelliJ.
2. Open `pom.xml` and choose **Load Maven Project** / **Add as Maven Project** if needed.
3. Open:

```text
src/test/java/instructor
```

4. Right-click the `instructor` folder.
5. Choose **Run tests in instructor**.

Green tests mean that part of the assignment is passing.

---

# Completion checklist

Before calling the assignment finished, check:

- [ ] all 10 enums exist
- [ ] all 10 abstract classes exist
- [ ] all 19 concrete classes exist
- [ ] every package name matches exactly
- [ ] all instance fields are `private final`
- [ ] every constructor validates its own inputs
- [ ] `ClosedEconomyGDP` extends `NominalGDP`
- [ ] no formulas are duplicated in `Main`
- [ ] all enum metadata tests pass
- [ ] all calculation tests pass
- [ ] all validation tests pass
- [ ] all inheritance tests pass
- [ ] all polymorphism tests pass
- [ ] `MainDriverTest` passes

When every instructor test is green, you are done.
