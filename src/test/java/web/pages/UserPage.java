package web.pages;

import com.codeborne.selenide.SelenideElement;
import web.utils.XPathBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class UserPage {

    public SelenideElement fieldEnterFirstName() {
        return XPathBuilder.element("input", "id", "first_name_send");
    }

    public SelenideElement fieldEnterLastName() {
        return XPathBuilder.element("input", "id", "last_name_send");
    }

    public SelenideElement fieldEnterAge() {return XPathBuilder.element("input", "id", "age_send");}

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

    public SelenideElement getNewUserID() {
        return XPathBuilder.containsElement("button", "@class", "newId btn");
    }

    public SelenideElement fieldEnterUserId() { return XPathBuilder.element("input", "id", "id_send");}

}

