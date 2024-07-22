package steps.users.UI;

import steps.auth.UI.LoginStep;
import web.pages.CarsPage;
import web.pages.MainPage;
import web.pages.UserPage;
import web.steps.CommonWebSteps;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersUIStep {
    public static void createUserUi() {
        LoginStep.authorization();
        new CommonWebSteps().clickElement("Users", new MainPage().usersList())
                .clickElement("Create new", new MainPage().createNewUser());
        new CommonWebSteps().sendKeys("First Name", new UserPage().fieldEnterFirstName(), "Владимир")
                .sendKeys("Last Name", new UserPage().fieldEnterLastName(), "Гончаров")
                .sendKeys("Age",new UserPage().fieldEnterAge(),"25")
                .clickElement("Sex", new UserPage().radioSexMale())
                .sendKeys("Money", new UserPage().fieldEnterMoney(), "250000")
                .clickElement("Push to API", new UserPage().buttonPush());
    }
    public static void createUserUiWithoutAge() {
        LoginStep.authorization();
        new CommonWebSteps().clickElement("Users", new MainPage().usersList())
                .clickElement("Create new", new MainPage().createNewUser());
        new CommonWebSteps().sendKeys("First Name", new UserPage().fieldEnterFirstName(), "Роман")
                .sendKeys("Last Name", new UserPage().fieldEnterLastName(), "Романов")
                .clickElement("Sex", new UserPage().radioSexMale())
                .sendKeys("Money", new UserPage().fieldEnterMoney(), "27000")
                .clickElement("Push to API", new UserPage().buttonPush());
    }

    public static void createUserUiWithDoubleAge() {
        LoginStep.authorization();
        new CommonWebSteps().clickElement("Users", new MainPage().usersList())
                .clickElement("Create new", new MainPage().createNewUser());
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
        new CommonWebSteps().clickElement("Users", new MainPage().usersList())
                .clickElement("Add money", new MainPage().addMoney());
        new CommonWebSteps().sendKeys("User ID", new UserPage().fieldEnterUserId(), extractId)
                .sendKeys("Money", new UserPage().fieldEnterMoney(),"2000")
                .clickElement("Push to API", new UserPage().buttonPush());
    }
    public static void buyCarUi(String userID, String carID) {
        new CommonWebSteps().clickElement("Users", new MainPage().usersList())
                .clickElement("Bay or sell car", new MainPage().bayOrSellCar());
        new CommonWebSteps().sendKeys("User ID", new UserPage().fieldEnterUserId(), extractID(userID))
                .sendKeys("Car ID", new CarsPage().fieldEnterCarId(), extractID(carID))
                .clickElement("BUY",new UserPage().radioBuyCar())
                .clickElement("Push to api", new UserPage().buttonPush());
    }
}
