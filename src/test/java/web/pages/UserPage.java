package web.pages;

import com.codeborne.selenide.SelenideElement;
import web.utils.XPathBuilder;

public class UserPage {

    public SelenideElement fieldEnterFirstName() {
        return XPathBuilder.element("input", "id", "first_name_send");
    }

    public SelenideElement fieldEnterLastName() {
        return XPathBuilder.element("input", "id", "last_name_send");
    }

    public SelenideElement radioSexMale() {
        return XPathBuilder.element("input", "value", "MALE");
    }

    public SelenideElement fieldEnterMoney() {
        return XPathBuilder.element("input", "step", "0.01");
    }

    public SelenideElement buttonPush() {
        return XPathBuilder.containsElement("button", "@class", "tableButton btn");
    }

    public SelenideElement getStatus() {
        return XPathBuilder.containsElement("button", "@class", "status btn");
    }
}

