package web.pages;

import com.codeborne.selenide.SelenideElement;

public class MainPage {
    public SelenideElement fieldEnterEmail(){
        return XPathBuilder.element("input","name","email");
    }
    public SelenideElement fieldEnterPass(){
        return XPathBuilder.element("input","name","password");
    }
}
