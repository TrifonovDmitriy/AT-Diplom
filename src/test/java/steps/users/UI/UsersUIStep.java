package steps.users.UI;

import steps.auth.UI.LoginStep;
import web.pages.MainPage;
import web.pages.UserPage;
import web.steps.CommonWebSteps;

public class UsersUIStep {
    public static void createUserUi() {
        LoginStep.authorization();
        new CommonWebSteps().clickElement("Users", new MainPage().UsersList())
                .clickElement("Create new", new MainPage().CreateNewUser());
        new CommonWebSteps().sendKeys("First Name", new UserPage().fieldEnterFirstName(), "Василий")
                .sendKeys("Last Name", new UserPage().fieldEnterLastName(), "Романов")
                .sendKeys("Age",new UserPage().fieldEnterAge(),"18")
                .clickElement("Sex", new UserPage().radioSexMale())
                .sendKeys("Money", new UserPage().fieldEnterMoney(), "827000")
                .clickElement("Push to API", new UserPage().buttonPush());
    }
    public static void createUserUiWithoutAge() {
        LoginStep.authorization();
        new CommonWebSteps().clickElement("Users", new MainPage().UsersList())
                .clickElement("Create new", new MainPage().CreateNewUser());
        new CommonWebSteps().sendKeys("First Name", new UserPage().fieldEnterFirstName(), "Роман")
                .sendKeys("Last Name", new UserPage().fieldEnterLastName(), "Романов")
                .clickElement("Sex", new UserPage().radioSexMale())
                .sendKeys("Money", new UserPage().fieldEnterMoney(), "27000")
                .clickElement("Push to API", new UserPage().buttonPush());
    }

    public static void createUserUiWithDoubleAge() {
        LoginStep.authorization();
        new CommonWebSteps().clickElement("Users", new MainPage().UsersList())
                .clickElement("Create new", new MainPage().CreateNewUser());
        new CommonWebSteps().sendKeys("First Name", new UserPage().fieldEnterFirstName(), "Николай")
                .sendKeys("Last Name", new UserPage().fieldEnterLastName(), "Гоголь")
                .sendKeys("Age",new UserPage().fieldEnterAge(),"100.5")
                .clickElement("Sex", new UserPage().radioSexMale())
                .sendKeys("Money", new UserPage().fieldEnterMoney(), "0")
                .clickElement("Push to API", new UserPage().buttonPush());
    }
}
