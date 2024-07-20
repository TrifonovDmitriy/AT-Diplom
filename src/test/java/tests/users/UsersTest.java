package tests.users;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;
import steps.users.API.UsersApiStep;
import steps.users.UI.UsersUIStep;
import web.pages.UserPage;

public class UsersTest {
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
    @Owner("Trifonov Dmitriy")
    @DisplayName("Создание пользователя, UI")
    public void userCreateUi() {
        UsersUIStep.createUserUi();
        new UserPage().getNewUserID().should(Condition.visible);
        String actualTextStatus = new UserPage().getStatus().getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Status: Successfully pushed, code: 201", actualTextStatus, "Ожидаемый текст, не соответсвует действительному!");
        String actualTextUser = new UserPage().getNewUserID().getText();
        softAssert.assertTrue(actualTextUser.contains("New user ID"), "Поле не содержит: New user ID");
        softAssert.assertAll();
    }
    @Test
    @Owner("Trifonov Dmitriy")
    @DisplayName("Создание пользователя, UI (Негативный, без указания возраста)")
    public void userCreateUIWithoutAge() {
        UsersUIStep.createUserUiWithoutAge();
        String actualTextStatus = new UserPage().getStatus().getText();
        Assertions.assertEquals("Status: Invalid request data", actualTextStatus, "Ожидаемый текст, не соответсвует действительному!");
    }
}
