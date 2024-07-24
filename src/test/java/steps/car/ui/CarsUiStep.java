package steps.car.UI;

import steps.auth.UI.LoginStep;
import web.pages.CarsPage;
import web.pages.MainPage;
import web.steps.CommonWebSteps;

public class CarsUiStep {
    public void carsReadAll(){
        LoginStep.authorization();
        new CommonWebSteps().clickElement("Cars",new MainPage().carsList())
                .clickElement("Read All", new MainPage().readAllCars());
    }
    public void createNewCar(){
        new CommonWebSteps().clickElement("Cars",new MainPage().carsList())
                .clickElement("Create New", new MainPage().createNewCar());
        new CommonWebSteps().sendKeys("Engine Type", new CarsPage().fieldEnterEngineType(),"Gasoline")
                .sendKeys("Mark", new CarsPage().fieldEnterMark(),"Toyota")
                .sendKeys("Model", new CarsPage().fieldEnterModel(),"Land Cruiser")
                .sendKeys("Price", new CarsPage().fieldEnterPrice(),"10000")
                .clickElement("Push to API", new CarsPage().buttonPush());
    }
    public void createNewCarWithWrongEngine(){
        new CommonWebSteps().clickElement("Cars",new MainPage().carsList())
                .clickElement("Create New", new MainPage().createNewCar());
        new CommonWebSteps().sendKeys("Engine Type", new CarsPage().fieldEnterEngineType(),"Gas")
                .sendKeys("Mark", new CarsPage().fieldEnterMark(),"AUDI")
                .sendKeys("Model", new CarsPage().fieldEnterModel(),"A5")
                .sendKeys("Price", new CarsPage().fieldEnterPrice(),"20000")
                .clickElement("Push to API", new CarsPage().buttonPush());
    }
}
