package web.pages;

import com.codeborne.selenide.SelenideElement;
import web.utils.XPathBuilder;

/**
 * Класс возвращает элементы страницы авторизации
 */
public class MainPage {
    public SelenideElement fieldEnterEmail() {
        return XPathBuilder.element("input", "name", "email");
    }

    public SelenideElement fieldEnterPass() {
        return XPathBuilder.element("input", "name", "password");
    }

    public SelenideElement buttonGo() {
        return XPathBuilder.containsElement("button", "text()", " GO");
    }

    public SelenideElement UsersList() {
        return XPathBuilder.containsElement("a", "text()", "Users");
    }

    public SelenideElement CreateNewUser() {
        return XPathBuilder.containsElement("a", "text()", "Create new");
    }
}
