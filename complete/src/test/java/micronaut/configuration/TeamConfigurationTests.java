package micronaut.configuration;

import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamConfigurationTests {

    //tag::teamConfigSpecNoBuilder[]
    @Test
    void testTeamConfiguration() {
        Map<String, Object> items = new HashMap<>();
        items.put("team.name", "evolution");
        items.put("team.color", "green");
        List<String> names = new ArrayList<>();
        names.add("Nirav Assar");
        names.add("Lionel Messi");

        items.put("team.player-names", names);
        ApplicationContext ctx = ApplicationContext.run(ApplicationContext.class, items); // <1>
        TeamConfiguration teamConfiguration = ctx.getBean(TeamConfiguration.class);

        assertEquals("evolution", teamConfiguration.getName());
        assertEquals("green", teamConfiguration.getColor());
        assertEquals("Nirav Assar", teamConfiguration.getPlayerNames().get(0));
        assertEquals("Lionel Messi", teamConfiguration.getPlayerNames().get(1));
    }
    //end::teamConfigSpecNoBuilder[]

    @Test
    void testBuilderPatternPlainUsage() {
        TeamAdmin teamAdmin = new TeamAdmin.Builder().withManager("Nirav")
                .withCoach("Tommy")
                .withPresident("Mark").build();

        assertEquals("Nirav", teamAdmin.getManager());
        assertEquals("Tommy", teamAdmin.getCoach());
        assertEquals("Mark", teamAdmin.getPresident());
    }

    //tag::teamConfigSpecBuilder[]
    @Test
    void testTeamConfigurationBuilder() {
        Map<String, Object> items = new HashMap<>();
        items.put("team.name", "evolution");
        items.put("team.color", "green");
        List<String> names = new ArrayList<>();
        names.add("Nirav Assar");
        names.add("Lionel Messi");
        items.put("team.team-admin.manager", "Jerry Jones"); // <1>
        items.put("team.team-admin.coach", "Tommy O'Neill");
        items.put("team.team-admin.president", "Mark Scanell");

        items.put("team.player-names", names);
        ApplicationContext ctx = ApplicationContext.run(ApplicationContext.class, items);
        TeamConfiguration teamConfiguration = ctx.getBean(TeamConfiguration.class);
        TeamAdmin teamAdmin = teamConfiguration.builder.build(); // <2>

        assertEquals("evolution", teamConfiguration.getName());
        assertEquals("green", teamConfiguration.getColor());
        assertEquals("Nirav Assar", teamConfiguration.getPlayerNames().get(0));
        assertEquals("Lionel Messi", teamConfiguration.getPlayerNames().get(1));

        // check the builder has values set
        assertEquals("Jerry Jones", teamConfiguration.builder.getManager());
        assertEquals("Tommy O'Neill", teamConfiguration.builder.getCoach());
        assertEquals("Mark Scanell", teamConfiguration.builder.getPresident());

        // check the object can be built
        assertEquals("Jerry Jones", teamAdmin.getManager()); // <3>
        assertEquals("Tommy O'Neill", teamAdmin.getCoach());
        assertEquals("Mark Scanell", teamAdmin.getPresident());

    }
    //end::teamConfigSpecBuilder[]
}
