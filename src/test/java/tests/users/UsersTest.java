package tests.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.users.API.UsersApiStep;

public class UsersTest {
    @Test
    @DisplayName("Создание пользователя, API (Позитивный)")
    public void userCreate(){
        new UsersApiStep().createUserApi( "55","Михаил", "20500","Кутузов", "MALE");
    }
}
