package web.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
/**
 * Класс описывает общие веб шаги
 */
public class CommonWebSteps {
    @Step("Клик по элементу '{elementName}'")
    public CommonWebSteps clickElement(String elementName, SelenideElement element){
        element.shouldBe(visible,Duration.ofSeconds(15)).click();
        return this;
    }
    @Step("Ввод в элемент '{elementName}' текст '{text}'")
    public CommonWebSteps sendKeys(String elementName, SelenideElement element, String text){
        element.shouldBe(visible, Duration.ofSeconds(5)).sendKeys(text);
        return this;
    }
    @Step("Подтверждение alerta")
    public CommonWebSteps acceptAlert(){
        Selenide.switchTo().alert().accept();
        return this;
    }
}
