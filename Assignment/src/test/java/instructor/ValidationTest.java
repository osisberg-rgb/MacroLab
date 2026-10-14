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

class ValidationTest {
    private static final double[] NONFINITE = {
            Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY
    };

    @Test
    void gdpInputsFollowPositiveNonnegativeAndSignedRules() {
        assertThrows(IllegalArgumentException.class, () -> new NominalGDP(-1, 1, 1, 0));
        assertThrows(IllegalArgumentException.class, () -> new NominalGDP(1, -1, 1, 0));
        assertThrows(IllegalArgumentException.class, () -> new NominalGDP(1, 1, -1, 0));
        assertDoesNotThrow(() -> new NominalGDP(1, 1, 1, -2));
        assertThrows(IllegalArgumentException.class, () -> new NominalGDP(0, 0, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new NominalGDP(1, 0, 0, -1));

        for (double invalid : NONFINITE) {
            assertThrows(IllegalArgumentException.class, () -> new NominalGDP(1, 1, 1, invalid));
        }

        assertThrows(IllegalArgumentException.class, () -> new RealGDP(0, 100));
        assertThrows(IllegalArgumentException.class, () -> new RealGDP(100, 0));
        assertThrows(IllegalArgumentException.class, () -> new GDPDeflator(0, 100));
        assertThrows(IllegalArgumentException.class, () -> new GDPDeflator(100, 0));
        assertThrows(IllegalArgumentException.class, () -> new RealGDPPerCapita(100, 0));
    }

    @Test
    void priceInputsMustBePositiveAndFinite() {
        assertThrows(IllegalArgumentException.class, () -> new ConsumerPriceIndex(0, 100));
        assertThrows(IllegalArgumentException.class, () -> new ConsumerPriceIndex(100, 0));
        assertThrows(IllegalArgumentException.class, () -> new InflationRate(0, 100));
        assertThrows(IllegalArgumentException.class, () -> new InflationRate(100, 0));
        for (double invalid : NONFINITE) {
            assertThrows(IllegalArgumentException.class, () -> new ConsumerPriceIndex(invalid, 100));
            assertThrows(IllegalArgumentException.class, () -> new InflationRate(100, invalid));
        }
    }

    @Test
    void laborMetricsRejectImpossiblePopulationRelationships() {
        assertThrows(IllegalArgumentException.class, () -> new UnemploymentRate(-1, 100));
        assertThrows(IllegalArgumentException.class, () -> new UnemploymentRate(10, 0));
        assertThrows(IllegalArgumentException.class, () -> new UnemploymentRate(101, 100));
        assertDoesNotThrow(() -> new UnemploymentRate(0, 100));
        assertDoesNotThrow(() -> new UnemploymentRate(100, 100));

        assertThrows(IllegalArgumentException.class, () -> new LaborForceParticipationRate(-1, 100));
        assertThrows(IllegalArgumentException.class, () -> new LaborForceParticipationRate(10, 0));
        assertThrows(IllegalArgumentException.class, () -> new LaborForceParticipationRate(101, 100));
        assertDoesNotThrow(() -> new LaborForceParticipationRate(0, 100));
        assertDoesNotThrow(() -> new LaborForceParticipationRate(100, 100));
    }

    @Test
    void savingInputsAllowNegativeResultsButValidateConstructorData() {
        assertDoesNotThrow(() -> new PrivateSaving(100, 90, 50));
        assertDoesNotThrow(() -> new PublicSaving(50, 100));
        assertThrows(IllegalArgumentException.class, () -> new PrivateSaving(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new PublicSaving(-1, 0));
        assertDoesNotThrow(() -> new NationalSaving(-50, 20));
        for (double invalid : NONFINITE) {
            assertThrows(IllegalArgumentException.class, () -> new NationalSaving(invalid, 0));
        }
    }

    @Test
    void reserveRequirementsAndReserveAmountsAreValidated() {
        for (double invalid : new double[]{0, -1, 100.0001, Double.NaN,
                Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class, () -> new MoneyMultiplier(invalid));
            assertThrows(IllegalArgumentException.class, () -> new RequiredReserves(100, invalid));
        }
        assertDoesNotThrow(() -> new MoneyMultiplier(100));
        assertDoesNotThrow(() -> new RequiredReserves(0, 10));
        assertThrows(IllegalArgumentException.class, () -> new RequiredReserves(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> new ExcessReserves(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new ExcessReserves(0, -1));
    }

    @Test
    void taxRatesAndSpendingAmountsAreValidated() {
        for (double invalidRate : new double[]{-0.1, 100.1, Double.NaN,
                Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class, () -> new FlatIncomeTaxRevenue(100, invalidRate));
            assertThrows(IllegalArgumentException.class, () -> new SalesTaxRevenue(100, invalidRate));
        }
        assertDoesNotThrow(() -> new FlatIncomeTaxRevenue(0, 0));
        assertDoesNotThrow(() -> new SalesTaxRevenue(100, 100));
        assertThrows(IllegalArgumentException.class, () -> new FlatIncomeTaxRevenue(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> new SalesTaxRevenue(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> new GovernmentPurchases(-1));
        assertThrows(IllegalArgumentException.class, () -> new TransferPayments(-1));
    }
}
