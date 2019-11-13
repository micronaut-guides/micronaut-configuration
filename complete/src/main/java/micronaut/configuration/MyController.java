package micronaut.configuration;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.annotation.Nullable;
import javax.inject.Named;

@Controller("/my")
public class MyController {

    private final TeamConfiguration teamConfiguration;
    private final StadiumConfiguration stadiumConfiguration;

    public MyController(@Nullable TeamConfiguration teamConfiguration, @Nullable @Named("pnc") StadiumConfiguration stadiumConfiguration) { // <1>
        this.teamConfiguration = teamConfiguration;
        this.stadiumConfiguration = stadiumConfiguration;
    }

    @Get("/team")
    public HttpResponse<TeamConfiguration> team() {
        return HttpResponse.ok(this.teamConfiguration);
    }

    @Get("/stadium")
    public  HttpResponse<StadiumConfiguration> stadium() {
        return HttpResponse.ok(this.stadiumConfiguration);
    }
}
