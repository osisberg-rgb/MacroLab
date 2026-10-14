package instructor;

import Economy.Enums.ComponentCategory;
import Economy.Metrics.Enums.MetricGroup;
import Economy.Metrics.Output.Enums.OutputMetricName;
import Economy.Metrics.Prices.Enums.PriceMetricName;
import Economy.Metrics.Labor.Enums.LaborMetricName;
import Economy.Metrics.Saving.Enums.SavingMetricName;
import Economy.Metrics.Banking.Enums.BankingMetricName;
import Economy.Policy.Enums.PolicyGroup;
import Economy.Policy.Tax.Enums.TaxPolicyName;
import Economy.Policy.Spending.Enums.SpendingPolicyName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EnumMetadataTest {
    record Expected(Class<? extends Enum<?>> type, String[] constants, String[] names, String[] descriptions) {}

    private static final Expected[] ALL = {
            new Expected(ComponentCategory.class,
                    new String[]{"MACRO_METRIC", "FISCAL_POLICY"},
                    new String[]{"Macro metric", "Fiscal policy"},
                    new String[]{"A calculated measure used to describe the macroeconomy.",
                            "A government tax or spending item with a budget effect."}),
            new Expected(MetricGroup.class,
                    new String[]{"OUTPUT", "PRICES", "LABOR", "SAVING", "BANKING"},
                    new String[]{"Output", "Prices", "Labor", "Saving", "Banking"},
                    new String[]{"Measures production and real economic activity.",
                            "Measures the price level and changes in prices.",
                            "Measures employment and participation in the labor market.",
                            "Measures private, public, and national saving.",
                            "Measures reserve requirements and deposit expansion."}),
            new Expected(PolicyGroup.class,
                    new String[]{"TAX", "SPENDING"},
                    new String[]{"Tax", "Spending"},
                    new String[]{"A fiscal policy item that raises government revenue.",
                            "A fiscal policy item that uses government funds."}),
            new Expected(OutputMetricName.class,
                    new String[]{"NOMINAL_GDP", "CLOSED_ECONOMY_GDP", "REAL_GDP", "GDP_DEFLATOR", "REAL_GDP_PER_CAPITA"},
                    new String[]{"Nominal GDP", "Closed-economy GDP", "Real GDP", "GDP deflator", "Real GDP per capita"},
                    new String[]{"The market value of final goods and services measured at current prices.",
                            "Nominal GDP for an economy with zero net exports.",
                            "Output measured after adjusting nominal GDP for the price level.",
                            "A price index comparing nominal GDP with real GDP.",
                            "Real GDP divided by population."}),
            new Expected(PriceMetricName.class,
                    new String[]{"CONSUMER_PRICE_INDEX", "INFLATION_RATE"},
                    new String[]{"Consumer Price Index", "Inflation rate"},
                    new String[]{"The cost of a fixed basket relative to its cost in the base period.",
                            "The percentage change in the price index from the previous period."}),
            new Expected(LaborMetricName.class,
                    new String[]{"UNEMPLOYMENT_RATE", "LABOR_FORCE_PARTICIPATION_RATE"},
                    new String[]{"Unemployment rate", "Labor-force participation rate"},
                    new String[]{"The unemployed as a percentage of the labor force.",
                            "The labor force as a percentage of the adult population."}),
            new Expected(SavingMetricName.class,
                    new String[]{"PRIVATE_SAVING", "PUBLIC_SAVING", "NATIONAL_SAVING"},
                    new String[]{"Private saving", "Public saving", "National saving"},
                    new String[]{"Household income remaining after taxes and consumption.",
                            "Net tax revenue remaining after government purchases.",
                            "The sum of private saving and public saving."}),
            new Expected(BankingMetricName.class,
                    new String[]{"MONEY_MULTIPLIER", "REQUIRED_RESERVES", "EXCESS_RESERVES"},
                    new String[]{"Money multiplier", "Required reserves", "Excess reserves"},
                    new String[]{"The reciprocal of the required reserve ratio.",
                            "Deposits multiplied by the required reserve ratio.",
                            "Actual reserves minus required reserves."}),
            new Expected(TaxPolicyName.class,
                    new String[]{"FLAT_INCOME_TAX_REVENUE", "SALES_TAX_REVENUE"},
                    new String[]{"Flat income-tax revenue", "Sales-tax revenue"},
                    new String[]{"Revenue from applying one tax rate to taxable income.",
                            "Revenue from applying a tax rate to taxable spending."}),
            new Expected(SpendingPolicyName.class,
                    new String[]{"GOVERNMENT_PURCHASES", "TRANSFER_PAYMENTS"},
                    new String[]{"Government purchases", "Transfer payments"},
                    new String[]{"Government spending on newly produced goods and services.",
                            "Government payments that are not purchases of newly produced output."})
    };

    @Test
    void allEnumsUseTheRequiredConstantsNamesDescriptionsAndOrder() throws Exception {
        for (Expected expected : ALL) {
            assertTrue(expected.type().isEnum());
            assertTrue(Modifier.isPublic(expected.type().getModifiers()));
            Enum<?>[] values = expected.type().getEnumConstants();
            assertArrayEquals(expected.constants(), Arrays.stream(values).map(Enum::name).toArray(String[]::new));
            assertEquals(expected.names().length, values.length);

            for (int i = 0; i < values.length; i++) {
                String name = (String) expected.type().getMethod("getName").invoke(values[i]);
                String description = (String) expected.type().getMethod("getDescription").invoke(values[i]);
                assertEquals(expected.names()[i], name);
                assertEquals(expected.names()[i], values[i].toString());
                assertEquals(expected.descriptions()[i], description);
            }
        }
    }

    @Test
    void allEnumsStoreMetadataInPrivateFinalStringFields() {
        for (Expected expected : ALL) {
            var fields = Arrays.stream(expected.type().getDeclaredFields())
                    .filter(field -> !field.isSynthetic() && !Modifier.isStatic(field.getModifiers())
                            && field.getType() == String.class)
                    .toList();
            assertTrue(fields.size() >= 2, expected.type().getName());
            for (var field : fields) {
                assertTrue(Modifier.isPrivate(field.getModifiers()), field.toString());
                assertTrue(Modifier.isFinal(field.getModifiers()), field.toString());
            }
        }
    }
}
