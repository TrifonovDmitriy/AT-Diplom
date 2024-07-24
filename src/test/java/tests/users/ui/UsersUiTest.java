package tests.users.ui;

import base.GeneralBasic;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;
import steps.car.API.CarAPI;
import steps.car.UI.CarsUiStep;
import steps.users.API.UsersApiStep;
import steps.users.UI.UsersUIStep;
import utils.DBUtils;
import web.pages.UserPage;

import java.time.Duration;
import java.util.logging.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;
import static steps.users.UI.UsersUIStep.extractID;

public class UsersUiTest extends GeneralBasic {
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
        String userIdStr = actualTextUser.replaceAll("\\D+", "");
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
        sleep(200);
        String userID = new UserPage().getNewUserID().shouldBe(visible, Duration.ofSeconds(30)).getText();
        System.out.println(userID);
        UsersUIStep.addMoneyUi(extractID(userID));
        String actualStatusCode = new UserPage().getStatus().shouldBe(visible, Duration.ofSeconds(30)).getText();
        Assertions.assertTrue(actualStatusCode.contains("Status: Successfully pushed, code: 200"), "Ожидаемый текст не соответствует действительному!");
    }

    @Test
    @DisplayName("Создание пользователя, Пополнение денег, Создание автомобиля, Покупка автомобиля")
    @Owner("Trifonov Dmitriy")
    public void endToEndUserMoneyCarBuyUi() {
        UsersUIStep.createUserUi();
        sleep(100);
        String userID = new UserPage().getNewUserID().shouldBe(Condition.appear).shouldBe(visible, Duration.ofSeconds(5)).getText();
        Logger.getGlobal().info(userID);
        UsersUIStep.addMoneyUi(extractID(userID));
        new CarsUiStep().createNewCar();
        sleep(100);
        String carID = new UserPage().getNewUserID().shouldBe(Condition.appear).shouldBe(visible, Duration.ofSeconds(5)).getText();
        Logger.getGlobal().info(carID);
        UsersUIStep.buyCarUi(userID, carID);
        sleep(300);
        String actualStatusCode = new UserPage().getStatus().shouldBe(Condition.appear).shouldBe(visible, Duration.ofSeconds(5)).getText();
        Assertions.assertTrue(actualStatusCode.contains("Status: Successfully pushed, code: 200"), "Ожидаемый текст не соответствует действительному!");
        int userIDint = Integer.parseInt(extractID(userID));
        int carIDint = Integer.parseInt(extractID(carID));
        DBUtils.getCar(carIDint);
        DBUtils.getUser(userIDint);
    }
}
