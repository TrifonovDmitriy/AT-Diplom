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

    public SelenideElement usersList() {
        return XPathBuilder.containsElement("a", "text()", "Users");
    }

    public SelenideElement createNewUser() {
        return XPathBuilder.containsElement("a", "text()", "Create new");
    }
    public SelenideElement carsList() {
        return XPathBuilder.containsElement("a","text()","Cars");
    }
    public SelenideElement readAllCars(){
        return XPathBuilder.containsElement("a","text()","Read all");
    }
    public SelenideElement addMoney() {
        return XPathBuilder.elementText("a", "text", "Add money");
    }
    public SelenideElement createNewCar() {
        return XPathBuilder.numberElement("div", "class", "dropdown-menu show","//a[2]");
    }
    public SelenideElement bayOrSellCar() {return XPathBuilder.elementText("a", "text", "Buy or sell car");
    }
}
