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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EncapsulationTest {
    @Test
    void allStoredInstanceDataIsPrivateAndFinal() {
        Class<?>[] types = {
                NominalGDP.class, ClosedEconomyGDP.class, RealGDP.class, GDPDeflator.class,
                RealGDPPerCapita.class, ConsumerPriceIndex.class, InflationRate.class,
                UnemploymentRate.class, LaborForceParticipationRate.class, PrivateSaving.class,
                PublicSaving.class, NationalSaving.class, MoneyMultiplier.class,
                RequiredReserves.class, ExcessReserves.class, FlatIncomeTaxRevenue.class,
                SalesTaxRevenue.class, GovernmentPurchases.class, TransferPayments.class
        };

        for (Class<?> type : types) {
            for (Class<?> current = type; current != Object.class; current = current.getSuperclass()) {
                Class<?> owner = current;
                Arrays.stream(current.getDeclaredFields())
                        .filter(field -> !field.isSynthetic() && !Modifier.isStatic(field.getModifiers()))
                        .forEach(field -> {
                            assertTrue(Modifier.isPrivate(field.getModifiers()),
                                    owner.getName() + "." + field.getName());
                            assertTrue(Modifier.isFinal(field.getModifiers()),
                                    owner.getName() + "." + field.getName());
                        });
            }
        }
    }
}
