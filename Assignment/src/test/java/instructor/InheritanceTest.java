package instructor;

import Economy.EconomicComponent;
import Economy.Metrics.MacroMetric;
import Economy.Metrics.Output.*;
import Economy.Metrics.Prices.*;
import Economy.Metrics.Labor.*;
import Economy.Metrics.Saving.*;
import Economy.Metrics.Banking.*;
import Economy.Policy.FiscalPolicy;
import Economy.Policy.Tax.*;
import Economy.Policy.Spending.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InheritanceTest {
    @Test
    void abstractHierarchyUsesRequiredDirectParents() {
        assertEquals(EconomicComponent.class, MacroMetric.class.getSuperclass());
        assertEquals(MacroMetric.class, OutputMetric.class.getSuperclass());
        assertEquals(MacroMetric.class, PriceMetric.class.getSuperclass());
        assertEquals(MacroMetric.class, LaborMetric.class.getSuperclass());
        assertEquals(MacroMetric.class, SavingMetric.class.getSuperclass());
        assertEquals(MacroMetric.class, BankingMetric.class.getSuperclass());

        assertEquals(EconomicComponent.class, FiscalPolicy.class.getSuperclass());
        assertEquals(FiscalPolicy.class, TaxPolicy.class.getSuperclass());
        assertEquals(FiscalPolicy.class, SpendingPolicy.class.getSuperclass());
    }

    @Test
    void metricClassesUseRequiredDirectParents() {
        assertEquals(OutputMetric.class, NominalGDP.class.getSuperclass());
        assertEquals(NominalGDP.class, ClosedEconomyGDP.class.getSuperclass());
        assertEquals(OutputMetric.class, RealGDP.class.getSuperclass());
        assertEquals(OutputMetric.class, GDPDeflator.class.getSuperclass());
        assertEquals(OutputMetric.class, RealGDPPerCapita.class.getSuperclass());

        assertEquals(PriceMetric.class, ConsumerPriceIndex.class.getSuperclass());
        assertEquals(PriceMetric.class, InflationRate.class.getSuperclass());
        assertEquals(LaborMetric.class, UnemploymentRate.class.getSuperclass());
        assertEquals(LaborMetric.class, LaborForceParticipationRate.class.getSuperclass());
        assertEquals(SavingMetric.class, PrivateSaving.class.getSuperclass());
        assertEquals(SavingMetric.class, PublicSaving.class.getSuperclass());
        assertEquals(SavingMetric.class, NationalSaving.class.getSuperclass());
        assertEquals(BankingMetric.class, MoneyMultiplier.class.getSuperclass());
        assertEquals(BankingMetric.class, RequiredReserves.class.getSuperclass());
        assertEquals(BankingMetric.class, ExcessReserves.class.getSuperclass());
    }

    @Test
    void fiscalPolicyClassesUseRequiredDirectParents() {
        assertEquals(TaxPolicy.class, FlatIncomeTaxRevenue.class.getSuperclass());
        assertEquals(TaxPolicy.class, SalesTaxRevenue.class.getSuperclass());
        assertEquals(SpendingPolicy.class, GovernmentPurchases.class.getSuperclass());
        assertEquals(SpendingPolicy.class, TransferPayments.class.getSuperclass());
    }
}
