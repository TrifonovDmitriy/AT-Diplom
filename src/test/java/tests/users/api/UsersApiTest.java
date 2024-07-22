package tests.users.api;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.users.API.UsersApiStep;

public class UsersApiTest {
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

}
