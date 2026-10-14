package instructor;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MainDriverTest {
    private static final List<String> EXPECTED = List.of(
            "Nominal GDP: component=Macro metric, group=Output, unit=currency, value=725.0000",
            "Closed-economy GDP: component=Macro metric, group=Output, unit=currency, value=750.0000",
            "Real GDP: component=Macro metric, group=Output, unit=currency, value=600.0000",
            "GDP deflator: component=Macro metric, group=Output, unit=index, value=125.0000",
            "Real GDP per capita: component=Macro metric, group=Output, unit=currency/person, value=2000.0000",
            "Consumer Price Index: component=Macro metric, group=Prices, unit=index, value=120.0000",
            "Inflation rate: component=Macro metric, group=Prices, unit=percent, value=4.3478",
            "Unemployment rate: component=Macro metric, group=Labor, unit=percent, value=4.0000",
            "Labor-force participation rate: component=Macro metric, group=Labor, unit=percent, value=80.0000",
            "Private saving: component=Macro metric, group=Saving, unit=currency, value=150.0000",
            "Public saving: component=Macro metric, group=Saving, unit=currency, value=-50.0000",
            "National saving: component=Macro metric, group=Saving, unit=currency, value=100.0000",
            "Money multiplier: component=Macro metric, group=Banking, unit=multiplier, value=10.0000",
            "Required reserves: component=Macro metric, group=Banking, unit=currency, value=100.0000",
            "Excess reserves: component=Macro metric, group=Banking, unit=currency, value=30.0000",
            "Flat income-tax revenue: component=Fiscal policy, group=Tax, unit=currency, value=200.0000",
            "Sales-tax revenue: component=Fiscal policy, group=Tax, unit=currency, value=30.0000",
            "Government purchases: component=Fiscal policy, group=Spending, unit=currency, value=250.0000",
            "Transfer payments: component=Fiscal policy, group=Spending, unit=currency, value=75.0000"
    );

    private static List<String> output() throws Exception {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try (PrintStream capture = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            System.setOut(capture);
            Class.forName("Main").getMethod("main", String[].class)
                    .invoke(null, (Object) new String[0]);
        } finally {
            System.setOut(original);
        }
        return bytes.toString(StandardCharsets.UTF_8).lines().toList();
    }

    @Test
    void mainPrintsTheCompleteMacroLabDemonstration() throws Exception {
        assertEquals(EXPECTED, output());
    }

    @Test
    void mainFormattingIsStableAcrossLocales() throws Exception {
        Locale original = Locale.getDefault(Locale.Category.FORMAT);
        try {
            Locale.setDefault(Locale.Category.FORMAT, Locale.GERMANY);
            assertEquals(EXPECTED, output());
        } finally {
            Locale.setDefault(Locale.Category.FORMAT, original);
        }
    }
}
