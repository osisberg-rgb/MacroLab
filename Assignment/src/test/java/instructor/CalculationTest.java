package instructor;

import Economy.Metrics.Output.*;
import Economy.Metrics.Prices.*;
import Economy.Metrics.Labor.*;
import Economy.Metrics.Saving.*;
import Economy.Metrics.Banking.*;
import Economy.Policy.Tax.*;
import Economy.Policy.Spending.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {
    private static void close(double expected, double actual) {
        double tolerance = Math.max(1e-12, Math.abs(expected) * 1e-12);
        assertTrue(Double.isFinite(actual));
        assertEquals(expected, actual, tolerance);
    }

    @Test
    void outputCalculationsUseEcon201Formulas() {
        close(725, new NominalGDP(500, 100, 150, -25).evaluate());
        close(750, new ClosedEconomyGDP(500, 100, 150).evaluate());
        close(600, new RealGDP(750, 125).evaluate());
        close(125, new GDPDeflator(750, 600).evaluate());
        close(2000, new RealGDPPerCapita(600_000, 300).evaluate());
    }

    @Test
    void closedEconomyGdpReusesNominalGdpBehavior() {
        for (double[] values : new double[][]{{100, 50, 25}, {0, 20, 5}, {1000, 200, 300}}) {
            NominalGDP open = new NominalGDP(values[0], values[1], values[2], 0);
            NominalGDP closed = new ClosedEconomyGDP(values[0], values[1], values[2]);
            close(open.evaluate(), closed.evaluate());
            assertEquals("Closed-economy GDP", closed.getName());
        }
    }

    @Test
    void priceCalculationsUseCpiAndInflationFormulas() {
        close(120, new ConsumerPriceIndex(240, 200).evaluate());
        close((120.0 - 115.0) / 115.0 * 100.0, new InflationRate(120, 115).evaluate());
        close(-5, new InflationRate(95, 100).evaluate());
    }

    @Test
    void laborCalculationsUseLaborForceAsTheCorrectDenominator() {
        close(4, new UnemploymentRate(8, 200).evaluate());
        close(80, new LaborForceParticipationRate(200, 250).evaluate());
    }

    @Test
    void savingCalculationsAllowDeficitsAndDissaving() {
        close(150, new PrivateSaving(1000, 200, 650).evaluate());
        close(-50, new PublicSaving(200, 250).evaluate());
        close(100, new NationalSaving(150, -50).evaluate());
        close(-40, new PrivateSaving(100, 90, 50).evaluate());
    }

    @Test
    void bankingCalculationsConvertPercentagesToDecimals() {
        close(10, new MoneyMultiplier(10).evaluate());
        close(100, new RequiredReserves(1000, 10).evaluate());
        close(30, new ExcessReserves(130, 100).evaluate());
        close(-20, new ExcessReserves(80, 100).evaluate());
    }

    @Test
    void fiscalPolicyCalculationsUseTaxBasesAndAmounts() {
        close(200, new FlatIncomeTaxRevenue(1000, 20).evaluate());
        close(30, new SalesTaxRevenue(500, 6).evaluate());
        close(250, new GovernmentPurchases(250).evaluate());
        close(75, new TransferPayments(75).evaluate());
    }
}
