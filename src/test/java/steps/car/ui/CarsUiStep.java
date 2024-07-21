package steps.car.ui;

import steps.auth.UI.LoginStep;
import web.pages.MainPage;
import web.steps.CommonWebSteps;

public class CarsUiStep {
    public void carsReadAll(){
        LoginStep.authorization();
        new CommonWebSteps().clickElement("Cars",new MainPage().carsList())
                .clickElement("Read All", new MainPage().readAllCars());
    }
}
