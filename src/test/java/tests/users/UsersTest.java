package tests.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.users.API.UsersApiStep;
import steps.users.UI.UsersUIStep;

public class UsersTest {
    @Test
    @DisplayName("Создание пользователя, API (Негативный, без указания возраста)")
    public void userCreateApiWithoutAge() {
        new UsersApiStep().createUserApi("", "Михаил", "20500", "Кутузов", "MALE");
    }

    @Test
    @DisplayName("Создание пользователя, UI (Негативный, без указания возраста)")
    public void userCreateUIWithoutAge() {
        UsersUIStep.createUserUI();
    }
}
