package instructor;

import Economy.EconomicComponent;
import Economy.Enums.ComponentCategory;
import Economy.Metrics.MacroMetric;
import Economy.Metrics.Enums.MetricGroup;
import Economy.Metrics.Output.*;
import Economy.Metrics.Prices.*;
import Economy.Metrics.Labor.*;
import Economy.Metrics.Saving.*;
import Economy.Metrics.Banking.*;
import Economy.Policy.FiscalPolicy;
import Economy.Policy.Enums.PolicyGroup;
import Economy.Policy.Tax.*;
import Economy.Policy.Spending.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolymorphismTest {
    @Test
    void macroMetricsDispatchThroughMacroMetricReferences() {
        MacroMetric[] metrics = {
                new NominalGDP(10, 20, 30, 5),
                new ConsumerPriceIndex(110, 100),
                new UnemploymentRate(5, 100),
                new NationalSaving(50, -10),
                new MoneyMultiplier(20)
        };

        assertArrayEquals(new MetricGroup[]{
                MetricGroup.OUTPUT, MetricGroup.PRICES, MetricGroup.LABOR,
                MetricGroup.SAVING, MetricGroup.BANKING
        }, java.util.Arrays.stream(metrics).map(MacroMetric::getGroup).toArray(MetricGroup[]::new));

        for (MacroMetric metric : metrics) {
            assertEquals(ComponentCategory.MACRO_METRIC, metric.getComponentCategory());
            assertNotNull(metric.getName());
            assertFalse(metric.getName().isBlank());
            assertNotNull(metric.getUnit());
            assertTrue(Double.isFinite(metric.evaluate()));
        }
    }

    @Test
    void fiscalPoliciesDispatchThroughFiscalPolicyReferences() {
        FiscalPolicy[] policies = {
                new FlatIncomeTaxRevenue(100, 20),
                new SalesTaxRevenue(100, 5),
                new GovernmentPurchases(30),
                new TransferPayments(10)
        };
        assertEquals(PolicyGroup.TAX, policies[0].getGroup());
        assertEquals(PolicyGroup.TAX, policies[1].getGroup());
        assertEquals(PolicyGroup.SPENDING, policies[2].getGroup());
        assertEquals(PolicyGroup.SPENDING, policies[3].getGroup());
        for (FiscalPolicy policy : policies) {
            assertEquals(ComponentCategory.FISCAL_POLICY, policy.getComponentCategory());
        }
    }

    @Test
    void componentsDispatchThroughRootReferences() {
        EconomicComponent[] components = {
                new RealGDP(125, 100),
                new InflationRate(105, 100),
                new GovernmentPurchases(50),
                new FlatIncomeTaxRevenue(200, 10)
        };

        assertEquals(ComponentCategory.MACRO_METRIC, components[0].getComponentCategory());
        assertEquals(ComponentCategory.MACRO_METRIC, components[1].getComponentCategory());
        assertEquals(ComponentCategory.FISCAL_POLICY, components[2].getComponentCategory());
        assertEquals(ComponentCategory.FISCAL_POLICY, components[3].getComponentCategory());
        assertArrayEquals(new double[]{125, 5, 50, 20},
                java.util.Arrays.stream(components).mapToDouble(EconomicComponent::evaluate).toArray(), 1e-12);
    }
}
