package tests.users;

import base.GeneralBasic;
import config.classes.MainProps;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;
import steps.car.ui.CarsUiStep;
import steps.users.API.UsersApiStep;
import steps.users.UI.UsersUIStep;
import web.pages.UserPage;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class UsersTest extends GeneralBasic {
    @Test
    @Owner("Trifonov Dmitriy")
    @DisplayName("Создание пользователя, API")
    public void userCreateApi() {
        new UsersApiStep().createUserApi();
    }

    @Test
    @Owner("Trifonov Dmitriy")
    @DisplayName("Создание и удаление пользователя, API")
    public void userDeleteApi() {
        new UsersApiStep().createUserApi().deleteUserApi();
    }
    @Test
    @Owner("Lozhkina Elena")
    @DisplayName("Создание пользователя и добавление денег, API")
    public void giveMoneyApi() {
        new UsersApiStep().createUserApi().giveMoneyToUser();
    }

    @Test
    @Owner("Trifonov Dmitriy")
    @DisplayName("Создание пользователя, UI")
    public void userCreateUi() {
        UsersUIStep.createUserUi();
        new UserPage().getNewUserID().should(visible);
        String actualTextStatus = new UserPage().getStatus().getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Status: Successfully pushed, code: 201", actualTextStatus, "Ожидаемый текст не соответствует действительному!");
        String actualTextUser = new UserPage().getNewUserID().getText();
        softAssert.assertTrue(actualTextUser.contains("New user ID"), "Поле не содержит: New user ID");
        String userIdStr = actualTextUser.replaceAll("\\D+","");
        int userID = Integer.parseInt(userIdStr);
        new UsersApiStep().deleteUserApi(userID);
        softAssert.assertAll();
    }
    @Test
    @Owner("Trifonov Dmitriy")
    @DisplayName("Создание пользователя, UI (Негативный, без указания возраста)")
    public void userCreateUIWithoutAge() {
        UsersUIStep.createUserUiWithoutAge();
        String actualTextStatus = new UserPage().getStatus().getText();
        Assertions.assertEquals("Status: Invalid request data", actualTextStatus, "Ожидаемый текст не соответствует действительному!");
    }

    @Test
    @Owner("Lozhkina Elena")
    @DisplayName("Создание пользователя, UI (Негативный, введен возраст c плавающей точкой)")
    public void userCreateUiWithDoubleAge() {
        UsersUIStep.createUserUiWithDoubleAge();
        String actualTextStatus = new UserPage().getStatus().getText();
        Assertions.assertEquals("Status: Invalid request data", actualTextStatus, "Ожидаемый текст не соответствует действительному!");
    }

    @Test
    @Owner("Lozhkina Elena")
    @DisplayName("Добавление денег, UI")
    public void addMoneyUiTest() {
        UsersUIStep.createUserUi();
        String userID = new UserPage().getNewUserID().shouldBe(visible, Duration.ofSeconds(30)).getText();
        System.out.println(userID);
        UsersUIStep.addMoneyUi(UsersUIStep.extractID(userID));
        String actualStatusCode = new UserPage().getStatus().shouldBe(visible, Duration.ofSeconds(30)).getText();
        Assertions.assertTrue(actualStatusCode.contains("Status: Successfully pushed, code: 200"), "Ожидаемый текст не соответствует действительному!");
    }
    @Test
    @DisplayName("Покупка автомобиля")
    @Owner("Trifonov Dmitriy")
    public void bayCar(){
        UsersUIStep.createUserUi();
        String userID = new UserPage().getNewUserID().shouldBe(visible, Duration.ofSeconds(30)).getText();
        System.out.println(userID);
        new CarsUiStep().createNewCar();
        String carID = new UserPage().getNewUserID().shouldBe(visible, Duration.ofSeconds(30)).getText();
        System.out.println(carID);
        UsersUIStep.BayCarUi(userID,carID);
        sleep(5000);
    }
}
