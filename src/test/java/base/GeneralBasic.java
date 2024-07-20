package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.classes.MainProps;
import config.interfaces.TestWebProps;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Общий класс для всех тестов
 */
public class GeneralBasic {
    public static TestWebProps props = MainProps.webProps;

    @BeforeAll
    public static void setWebdriverConf() {
        Configuration.pageLoadTimeout = props.pageLoadTimeOut();
        Configuration.timeout = props.timeOut();
        Configuration.browserSize = "1920x900";
        String WebDriverLocation = props.webdriverLocation();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("Chrome");
        capabilities.setVersion(props.webdriverVersion());
        capabilities.setCapability("enableVNC", true);
        if (WebDriverLocation != null) {
            System.setProperty("webdriver.chrome.driver", props.webdriverLocation());
            System.setProperty("selenide.browser", "Chrome");
        }
    }

    @AfterAll
    public static void after() {
        Selenide.closeWebDriver();
        WebDriverRunner.closeWebDriver();
    }
}
