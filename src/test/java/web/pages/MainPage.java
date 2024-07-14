package web.pages;

import com.codeborne.selenide.SelenideElement;
/**
 * Класс возвращает элементы страницы авторизации
 */
public class MainPage {
    public SelenideElement fieldEnterEmail(){
        return XPathBuilder.element("input","name","email");
    }
    public SelenideElement fieldEnterPass(){
        return XPathBuilder.element("input","name","password");
    }
    public SelenideElement buttonGo(){return  XPathBuilder.containsElement("button","text()"," GO");}
}
