package micronaut.configuration;

import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamConfigurationTests {

    @Test
    void testTeamConfiguration() {
        Map<String, Object> items = new HashMap<>();
        items.put("team.name", "evolution");
        items.put("team.color", "green");
        List<String> names = new ArrayList<>();
        names.add("Nirav Assar");
        names.add("Lionel Messi");

        items.put("team.player-names", names);
        ApplicationContext ctx = ApplicationContext.run(ApplicationContext.class, items);
        TeamConfiguration teamConfiguration = ctx.getBean(TeamConfiguration.class);

        assertEquals("evolution", teamConfiguration.getName());
        assertEquals("green", teamConfiguration.getColor());
        assertEquals("Nirav Assar", teamConfiguration.getPlayerNames().get(0));
        assertEquals("Lionel Messi", teamConfiguration.getPlayerNames().get(1));
    }

    @Test
    void testBuilderPatternPlainUsage() {
        TeamAdmin teamAdmin = new TeamAdmin.Builder().withManager("Nirav")
                .withCoach("Tommy")
                .withPresident("Mark").build();

        assertEquals("Nirav", teamAdmin.getManager());
        assertEquals("Tommy", teamAdmin.getCoach());
        assertEquals("Mark", teamAdmin.getPresident());
    }
}
