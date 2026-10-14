package instructor;

import Economy.Metrics.Output.*;
import Economy.Metrics.Prices.*;
import Economy.Metrics.Labor.*;
import Economy.Metrics.Saving.*;
import Economy.Metrics.Banking.*;
import Economy.Policy.Tax.*;
import Economy.Policy.Spending.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class ConstructorSignatureTest {
    private static void onePublicConstructor(Class<?> type, Class<?>... parameters) throws Exception {
        assertTrue(Modifier.isPublic(type.getModifiers()), type.getName());
        assertFalse(Modifier.isAbstract(type.getModifiers()), type.getName());
        assertEquals(1, type.getConstructors().length, type.getName());
        assertNotNull(type.getConstructor(parameters));
    }

    @Test
    void concreteClassesHaveExactlyTheRequiredPublicConstructors() throws Exception {
        onePublicConstructor(NominalGDP.class, double.class, double.class, double.class, double.class);
        onePublicConstructor(ClosedEconomyGDP.class, double.class, double.class, double.class);
        onePublicConstructor(RealGDP.class, double.class, double.class);
        onePublicConstructor(GDPDeflator.class, double.class, double.class);
        onePublicConstructor(RealGDPPerCapita.class, double.class, double.class);
        onePublicConstructor(ConsumerPriceIndex.class, double.class, double.class);
        onePublicConstructor(InflationRate.class, double.class, double.class);
        onePublicConstructor(UnemploymentRate.class, double.class, double.class);
        onePublicConstructor(LaborForceParticipationRate.class, double.class, double.class);
        onePublicConstructor(PrivateSaving.class, double.class, double.class, double.class);
        onePublicConstructor(PublicSaving.class, double.class, double.class);
        onePublicConstructor(NationalSaving.class, double.class, double.class);
        onePublicConstructor(MoneyMultiplier.class, double.class);
        onePublicConstructor(RequiredReserves.class, double.class, double.class);
        onePublicConstructor(ExcessReserves.class, double.class, double.class);
        onePublicConstructor(FlatIncomeTaxRevenue.class, double.class, double.class);
        onePublicConstructor(SalesTaxRevenue.class, double.class, double.class);
        onePublicConstructor(GovernmentPurchases.class, double.class);
        onePublicConstructor(TransferPayments.class, double.class);
    }
}
