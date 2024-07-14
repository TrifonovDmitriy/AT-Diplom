package web.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.utils.AllureHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

/**
 * Класс описывает общие веб шаги
 */
public class CommonWebSteps {
    @Step("Клик по элементу '{elementName}'")
    public CommonWebSteps clickElement(String elementName, SelenideElement element) {
        AllureHelper.takeScreenshotWeb("Клик по элементу " + elementName + ". Скриншот ДО");
        element.shouldBe(visible, Duration.ofSeconds(15)).click();
        AllureHelper.takeScreenshotWeb("Клик по элементу " + elementName + ". Скриншот ПОСЛЕ");
        return this;
    }

    @Step("Ввод в элемент '{elementName}' текст '{text}'")
    public CommonWebSteps sendKeys(String elementName, SelenideElement element, String text) {
        AllureHelper.takeScreenshotWeb("Ввод текста в поле " + elementName + ". Скриншот ДО");
        element.shouldBe(visible, Duration.ofSeconds(5)).sendKeys(text);
        AllureHelper.takeScreenshotWeb("Ввод текста в поле " + elementName + ". Скриншот ПОСЛЕ");
        return this;
    }

    @Step("Подтверждение алерта")
    public CommonWebSteps acceptAlert() {
        Selenide.switchTo().alert().accept();
        return this;
    }

    @Step("Подтверждение алерта с текстом '{expectedAlertText}'")
    public CommonWebSteps acceptAlert(String expectedAlertText) {
        String actualAlertText = Selenide.switchTo().alert().getText();
        if (actualAlertText.equals(expectedAlertText))
            Selenide.switchTo().alert().accept();
        else
            Assertions.fail("Ожидаемый текст алерта не совпал с фактическим. Ожидалось: " + expectedAlertText + ", на самом деле: " + actualAlertText);
        return this;
    }
}
