package web.pages;

import com.codeborne.selenide.SelenideElement;
import web.utils.XPathBuilder;

public class CarsPage {
    public SelenideElement buttonIdCars(){
        return XPathBuilder.numberElement("button","class","btn btn-secondary","2");
    }
    public SelenideElement idCarsTable(){
        return XPathBuilder.containsElementInclude("table","@class","table table-striped","/tbody[1]/tr[1]/td[1]");
    }
}
