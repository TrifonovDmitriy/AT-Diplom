package steps.users.UI;

import steps.auth.UI.LoginStep;
import web.pages.MainPage;
import web.pages.User;
import web.steps.CommonWebSteps;

public class UsersUIStep {
    public static void createUserUI(){
        LoginStep.authorization();
        new CommonWebSteps().clickElement("Users", new MainPage().UsersList())
                .clickElement("Create new", new MainPage().CreateNewUser());
        new CommonWebSteps().sendKeys("First Name", new User().fieldEnterFirstName(),"Роман")
                .sendKeys("Last Name", new User().fieldEnterLastName(),"Романов")
                .clickElement("Sex",new User().radioSexMale())
                .sendKeys("Money",new User().fieldEnterMoney(),"27000")
                .clickElement("Push to API",new User().buttonPush());
    }
}
