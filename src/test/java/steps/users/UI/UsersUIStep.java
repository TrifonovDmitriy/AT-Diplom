package steps.users.UI;

import steps.auth.UI.LoginStep;
import web.pages.MainPage;
import web.pages.UserPage;
import web.steps.CommonWebSteps;

public class UsersUIStep {
    public static void createUserUI() {
        LoginStep.authorization();
        new CommonWebSteps().clickElement("Users", new MainPage().UsersList())
                .clickElement("Create new", new MainPage().CreateNewUser());
        new CommonWebSteps().sendKeys("First Name", new UserPage().fieldEnterFirstName(), "Роман")
                .sendKeys("Last Name", new UserPage().fieldEnterLastName(), "Романов")
                .clickElement("Sex", new UserPage().radioSexMale())
                .sendKeys("Money", new UserPage().fieldEnterMoney(), "27000")
                .clickElement("Push to API", new UserPage().buttonPush());
    }
}
