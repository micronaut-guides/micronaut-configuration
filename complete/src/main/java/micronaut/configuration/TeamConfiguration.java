
package micronaut.configuration;

import io.micronaut.context.annotation.ConfigurationBuilder;
import io.micronaut.context.annotation.ConfigurationProperties;

import java.util.List;

//tag::teamConfigClassBuilder[]
//tag::teamConfigClassNoBuilder[]
@ConfigurationProperties("team")
class TeamConfiguration {
    private String name;
    private String color;
    private List<String> playerNames;
//end::teamConfigClassNoBuilder[]

    @ConfigurationBuilder(prefixes = "with", configurationPrefix = "team-admin") // <1>
    TeamAdmin.Builder builder = TeamAdmin.builder(); // <2>

    public TeamAdmin.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(TeamAdmin.Builder builder) {
        this.builder = builder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;
    }
}
//end::teamConfigClassBuilder[]
