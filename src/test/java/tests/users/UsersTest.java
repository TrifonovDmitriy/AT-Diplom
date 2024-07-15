package tests.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.users.API.UsersApiStep;

public class UsersTest {
    @Test
    @DisplayName("Создание пользователя")
    public void userCreate(){
        new UsersApiStep().createUserApi( "Иван", "Петров", "MALE");
    }
}
