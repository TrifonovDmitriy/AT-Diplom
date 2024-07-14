package web.utils;

import base.GeneralBasic;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

public class AllureHelper extends GeneralBasic {
    private static final Logger log = LoggerFactory.getLogger(AllureHelper.class);

    public static void takeScreenshotWeb(String screenName) {
        try {
            byte[] screen = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(screenName, new ByteArrayInputStream(screen));
        } catch (Exception e) {
            log.info("Не удалось сделать скриншот");
        }
    }
}
