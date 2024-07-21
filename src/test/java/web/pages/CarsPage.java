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
    public SelenideElement fieldEnterEngineType(){
        return XPathBuilder.element("input","id","car_engine_type_send");
    }
    public SelenideElement fieldEnterMark(){
        return XPathBuilder.element("input","id","car_mark_send");
    }
    public SelenideElement fieldEnterModel(){
        return XPathBuilder.element("input","id","car_model_send");
    }
    public SelenideElement fieldEnterPrice(){
        return XPathBuilder.element("input", "step", "0.01");
    }
    public SelenideElement buttonPush() {
        return XPathBuilder.containsElement("button", "@class", "tableButton btn");
    }
    public SelenideElement fieldEnterCarId() { return XPathBuilder.element("input", "id", "car_send");}
}