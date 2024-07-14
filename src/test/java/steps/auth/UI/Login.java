package steps.auth.UI;

import base.GeneralBasic;
import com.codeborne.selenide.Selenide;
import config.classes.MainProps;
import io.qameta.allure.Step;
import web.pages.MainPage;
import web.steps.CommonWebSteps;

import static com.codeborne.selenide.Selenide.open;

public class Login extends GeneralBasic {
    @Step("Авторизация")
    public static void authorization(){
        open(MainProps.webProps.getUrl());
        new CommonWebSteps().sendKeys("Enter your email", new MainPage().fieldEnterEmail(), "user@pflb.ru")
        .sendKeys("Enter your password", new MainPage().fieldEnterPass(), "user")
        .clickElement("GO", new MainPage().buttonGo())
        .acceptAlert("Successful authorization");
        Selenide.closeWebDriver();
    }
}