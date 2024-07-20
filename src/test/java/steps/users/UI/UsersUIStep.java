package steps.users.UI;

import steps.auth.UI.LoginStep;
import web.pages.MainPage;
import web.pages.UserPage;
import web.steps.CommonWebSteps;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersUIStep {
    public static void createUserUi() {
        LoginStep.authorization();
        new CommonWebSteps().clickElement("Users", new MainPage().UsersList())
                .clickElement("Create new", new MainPage().CreateNewUser());
        new CommonWebSteps().sendKeys("First Name", new UserPage().fieldEnterFirstName(), "Василий")
                .sendKeys("Last Name", new UserPage().fieldEnterLastName(), "Романов")
                .sendKeys("Age",new UserPage().fieldEnterAge(),"18")
                .clickElement("Sex", new UserPage().radioSexMale())
                .sendKeys("Money", new UserPage().fieldEnterMoney(), "8000")
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

    public static String extractID(String text) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        } else {
            throw new IllegalArgumentException("Нет чисел для извлечения в тексте: " + text);
        }
    }

    public static void addMoneyUi(String extractId) {
        new CommonWebSteps().clickElement("Users", new MainPage().UsersList())
                .clickElement("Add money", new MainPage().addMoney());

        new CommonWebSteps().sendID("User ID", new UserPage().fieldEnterUserId(), extractId)
                .sendKeys("Money", new UserPage().fieldEnterMoney(),"2000")
                .clickElement("Push to API", new UserPage().buttonPush());
    }
}
