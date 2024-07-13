package steps.auth.UI;


import base.GeneralBasic;
import com.codeborne.selenide.Selenide;
import config.classes.MainProps;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class Login extends GeneralBasic{
    @Test
    public void test1(){
        open(MainProps.webProps.getUrl());
        Selenide.closeWebDriver();
    }
}
