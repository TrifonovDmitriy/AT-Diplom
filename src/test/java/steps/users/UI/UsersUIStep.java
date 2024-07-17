package steps.users.UI;

import steps.auth.UI.LoginStep;
import web.pages.MainPage;
import web.steps.CommonWebSteps;

public class UsersUIStep {
    public static void createUserUI(){
        LoginStep.authorization();
        new CommonWebSteps().clickElement("Users", new MainPage().UsersList())
                .clickElement("Create new", new MainPage().CreateNewUser());
    }
}
