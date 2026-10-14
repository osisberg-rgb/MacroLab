import Economy.Metrics.MacroMetric;
import Economy.Metrics.Output.*;
import Economy.Metrics.Prices.*;
import Economy.Metrics.Labor.*;
import Economy.Metrics.Saving.*;
import Economy.Metrics.Banking.*;
import Economy.Policy.FiscalPolicy;
import Economy.Policy.Tax.*;
import Economy.Policy.Spending.*;

public class Main {
    public static void main(String[] args) {
        MacroMetric[] metrics = {
                new NominalGDP(500, 100, 150, -25),
                new ClosedEconomyGDP(500, 100, 150),
                new RealGDP(750, 125),
                new GDPDeflator(750, 600),
                new RealGDPPerCapita(600_000, 300),
                new ConsumerPriceIndex(240, 200),
                new InflationRate(120, 115),
                new UnemploymentRate(8, 200),
                new LaborForceParticipationRate(200, 250),
                new PrivateSaving(1000, 200, 650),
                new PublicSaving(200, 250),
                new NationalSaving(150, -50),
                new MoneyMultiplier(10),
                new RequiredReserves(1000, 10),
                new ExcessReserves(130, 100)
        };

        for (MacroMetric metric : metrics) {
            System.out.printf(java.util.Locale.ROOT,
                    "%s: component=%s, group=%s, unit=%s, value=%.4f%n",
                    metric.getName(), metric.getComponentCategory(), metric.getGroup(),
                    metric.getUnit(), metric.evaluate());
        }

        FiscalPolicy[] policies = {
                new FlatIncomeTaxRevenue(1000, 20),
                new SalesTaxRevenue(500, 6),
                new GovernmentPurchases(250),
                new TransferPayments(75)
        };

        for (FiscalPolicy policy : policies) {
            System.out.printf(java.util.Locale.ROOT,
                    "%s: component=%s, group=%s, unit=%s, value=%.4f%n",
                    policy.getName(), policy.getComponentCategory(), policy.getGroup(),
                    policy.getUnit(), policy.evaluate());
        }
    }
}
