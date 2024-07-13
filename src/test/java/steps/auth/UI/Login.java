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
        new CommonWebSteps().clickElement("Поле ввода логина", new MainPage().fieldEnterEmail());
        new CommonWebSteps().sendKeys("логин", new MainPage().fieldEnterEmail(), "user@pflb.ru");
        new CommonWebSteps().clickElement("Поле ввода пароля", new MainPage().fieldEnterEmail());
        new CommonWebSteps().sendKeys("пароль", new MainPage().fieldEnterPass(), "user");
        new CommonWebSteps().clickElement("GO", new MainPage().buttonGo());
        new CommonWebSteps().acceptAlert("Successful authorization");
        Selenide.closeWebDriver();
    }
}
