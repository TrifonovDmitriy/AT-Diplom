package tests.users;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    @DisplayName("Создание пользователя, UI (Негативный, без указания возраста)")
    public void userCreateUIWithoutAge() {
        UsersUIStep.createUserUI();
        String actualText = new UserPage().getStatus().getText();
        Assertions.assertEquals("Status: Invalid request data", actualText, "Ожидаемый текст, не соответсвует действительному!");
    }
}
