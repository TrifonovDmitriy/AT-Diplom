package steps.auth.UI;

import base.GeneralBasic;
import config.classes.MainProps;
import io.qameta.allure.Step;
import web.pages.MainPage;
import web.steps.CommonWebSteps;

import static com.codeborne.selenide.Selenide.open;

/**
 * Класс описывает шаги авторизации UI на странице
 */

public class LoginStep extends GeneralBasic {
    @Step("Авторизация")
    public static void authorization() {
        open(MainProps.webProps.getUrl());
        new CommonWebSteps().sendKeys("Enter your email", new MainPage().fieldEnterEmail(), MainProps.loginProps.apiLogin())
                .sendKeys("Enter your password", new MainPage().fieldEnterPass(), MainProps.loginProps.apiPassword())
                .clickElement("GO", new MainPage().buttonGo())
                .acceptAlert("Successful authorization");
    }
}