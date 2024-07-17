package config.interfaces;
import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/test/resources/configs/login.properties"
})

public interface TestLoginProps extends Config{
    @Key("sql.login")
    String sqlLogin();

    @Key("sql.password")
    String sqlPassword();

    @Key("api.login")
    String apiLogin();

    @Key("api.password")
    String apiPassword();
}
