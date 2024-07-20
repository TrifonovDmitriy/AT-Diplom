package config.interfaces;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/test/resources/configs/web.properties"
})

public interface TestWebProps extends Config {

    @Key("webdriverLocation")
    String webdriverLocation();

    @Key("timeout")
    @DefaultValue(value = "150000")
    int timeOut();

    @Key("pageLoadTimeOut")
    @DefaultValue(value = "120000")
    int pageLoadTimeOut();

    @Key("webdriverVersion")
    String webdriverVersion();

    @Key("url")
    String getUrl();
}
