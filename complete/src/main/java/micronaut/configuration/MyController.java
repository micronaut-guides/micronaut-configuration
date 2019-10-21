package micronaut.configuration

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

import javax.annotation.Nullable
import javax.inject.Named

@CompileStatic
@Controller("/my")
class MyController {

    TeamConfiguration teamConfiguration
    StadiumConfiguration stadiumConfiguration

    MyController(@Nullable TeamConfiguration teamConfiguration, @Nullable @Named("pnc") StadiumConfiguration stadiumConfiguration) { // <1>
        this.teamConfiguration = teamConfiguration
        this.stadiumConfiguration = stadiumConfiguration
    }

    @Get("/team")
    HttpResponse<TeamConfiguration> team() {
        return HttpResponse.ok(this.teamConfiguration)
    }

    @Get("/stadium")
    HttpResponse<StadiumConfiguration> stadium() {
        return HttpResponse.ok(this.stadiumConfiguration)
    }
}
