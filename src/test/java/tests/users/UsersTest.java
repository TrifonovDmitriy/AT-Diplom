package tests.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.users.API.UsersApiStep;
import steps.users.UI.UsersUIStep;

public class UsersTest {
    @Test
    @DisplayName("Создание пользователя, API")
    public void userCreateApi() {
        new UsersApiStep().createUserApi();
    }

    @Test
    @DisplayName("Создание и удаление пользователя, API")
    public void userDeleteApi() {
        new UsersApiStep().createUserApi().deleteUserApi();
    }

    @Test
    @DisplayName("Создание пользователя, UI (Негативный, без указания возраста)")
    public void userCreateUIWithoutAge() {
        UsersUIStep.createUserUI();
    }
}
