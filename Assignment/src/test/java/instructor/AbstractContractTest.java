package instructor;

import Economy.EconomicComponent;
import Economy.Enums.ComponentCategory;
import Economy.Metrics.MacroMetric;
import Economy.Metrics.Enums.MetricGroup;
import Economy.Metrics.Output.OutputMetric;
import Economy.Metrics.Prices.PriceMetric;
import Economy.Metrics.Labor.LaborMetric;
import Economy.Metrics.Saving.SavingMetric;
import Economy.Metrics.Banking.BankingMetric;
import Economy.Policy.FiscalPolicy;
import Economy.Policy.Enums.PolicyGroup;
import Economy.Policy.Tax.TaxPolicy;
import Economy.Policy.Spending.SpendingPolicy;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class AbstractContractTest {
    private static void requireAbstract(Class<?> owner, String name, Class<?> result, Class<?>... parameters)
            throws Exception {
        var method = owner.getDeclaredMethod(name, parameters);
        assertTrue(Modifier.isPublic(method.getModifiers()), owner.getSimpleName() + "." + name);
        assertTrue(Modifier.isAbstract(method.getModifiers()), owner.getSimpleName() + "." + name);
        assertFalse(Modifier.isStatic(method.getModifiers()), owner.getSimpleName() + "." + name);
        assertEquals(result, method.getReturnType());
    }

    @Test
    void rootAndIntermediateTypesArePublicAbstractClasses() {
        for (Class<?> type : new Class<?>[]{
                EconomicComponent.class, MacroMetric.class, OutputMetric.class, PriceMetric.class,
                LaborMetric.class, SavingMetric.class, BankingMetric.class, FiscalPolicy.class,
                TaxPolicy.class, SpendingPolicy.class}) {
            assertTrue(Modifier.isPublic(type.getModifiers()), type.getName());
            assertTrue(Modifier.isAbstract(type.getModifiers()), type.getName());
        }
    }

    @Test
    void economicComponentDeclaresTheSharedPolymorphicContract() throws Exception {
        requireAbstract(EconomicComponent.class, "getName", String.class);
        requireAbstract(EconomicComponent.class, "getUnit", String.class);
        requireAbstract(EconomicComponent.class, "evaluate", double.class);
        requireAbstract(EconomicComponent.class, "getComponentCategory", ComponentCategory.class);
    }

    @Test
    void macroAndPolicyBasesDeclareGroupContracts() throws Exception {
        requireAbstract(MacroMetric.class, "getGroup", MetricGroup.class);
        requireAbstract(FiscalPolicy.class, "getGroup", PolicyGroup.class);
    }

    @Test
    void categoryAndGroupMethodsAreOwnedByIntermediateBasesAndFinal() throws Exception {
        var macroCategory = MacroMetric.class.getDeclaredMethod("getComponentCategory");
        var policyCategory = FiscalPolicy.class.getDeclaredMethod("getComponentCategory");
        assertTrue(Modifier.isPublic(macroCategory.getModifiers()));
        assertTrue(Modifier.isFinal(macroCategory.getModifiers()));
        assertTrue(Modifier.isPublic(policyCategory.getModifiers()));
        assertTrue(Modifier.isFinal(policyCategory.getModifiers()));

        for (Class<?> type : new Class<?>[]{OutputMetric.class, PriceMetric.class, LaborMetric.class,
                SavingMetric.class, BankingMetric.class, TaxPolicy.class, SpendingPolicy.class}) {
            var method = type.getDeclaredMethod("getGroup");
            assertTrue(Modifier.isPublic(method.getModifiers()), type.getName());
            assertTrue(Modifier.isFinal(method.getModifiers()), type.getName());
        }
    }
}
