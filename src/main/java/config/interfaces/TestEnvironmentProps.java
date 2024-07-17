package config.interfaces;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/test/resources/configs/environment.properties"
})

public interface TestEnvironmentProps extends Config {
    @Key("db.Url")
    String dbUrl();

    @Key("api.Url")
    String apiUrl();
}
