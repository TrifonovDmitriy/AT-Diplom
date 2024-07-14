package tests.login;

import base.GeneralBasic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.auth.API.LoginApi;
import steps.auth.UI.Login;

public class LoginMainPageTest extends GeneralBasic {
    @Test
    @DisplayName("Вход в систему. UI Test")
    public void loginTest() {
        Login.authorization();
    }

    @Test
    @DisplayName("Вход в систему. API Test")
    public void loginApiTest() {
        new LoginApi().authorizationAPI("user@pflb.ru", "user", true);
    }
}
