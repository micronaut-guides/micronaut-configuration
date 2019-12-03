package example.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.inject.qualifiers.Qualifiers;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StadiumConfigurationTests {

    @Test
    void testStadiumConfiguration() {
        Map<String, Object> items = new HashMap<String, Object>() {{
            put("stadium.fenway.city", "Boston"); // <1>
            put("stadium.fenway.size", 60000);
            put("stadium.wrigley.city", "Chicago"); // <1>
            put("stadium.wrigley.size", 45000);
        }};

        ApplicationContext ctx = ApplicationContext.run(ApplicationContext.class, items);
        // <2>
        StadiumConfiguration fenwayConfiguration = ctx.getBean(StadiumConfiguration.class, Qualifiers.byName("fenway"));
        StadiumConfiguration wrigleyConfiguration = ctx.getBean(StadiumConfiguration.class, Qualifiers.byName("wrigley"));

        assertEquals("fenway", fenwayConfiguration.getName());
        assertEquals(60000, fenwayConfiguration.getSize());
        assertEquals("wrigley", wrigleyConfiguration.getName());
        assertEquals(45000, wrigleyConfiguration.getSize());

        ctx.close();
    }
}
