package tests.login;

import base.GeneralBasic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.auth.API.LoginApiStep;
import steps.auth.UI.LoginStep;

public class LoginMainPageTest extends GeneralBasic {
    @Test
    @DisplayName("Вход в систему. UI Test (Позитивный)")
    public void loginTest() {
        LoginStep.authorization();
    }

    @Test
    @DisplayName("Вход в систему. API Test (Позитивный)")
    public void loginApiTest() {
        new LoginApiStep().authorizationAPI("user@pflb.ru", "user");
    }
}
