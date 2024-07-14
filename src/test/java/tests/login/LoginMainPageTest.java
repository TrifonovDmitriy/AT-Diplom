package tests.login;

import base.GeneralBasic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.auth.UI.Login;

public class LoginMainPageTest extends GeneralBasic {
    @Test
    @DisplayName("Вход в систему")
    public void loginTest(){
        Login.authorization();
    }
}
