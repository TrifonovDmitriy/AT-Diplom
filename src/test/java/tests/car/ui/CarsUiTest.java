package tests.car.ui;

import base.GeneralBasic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;
import steps.auth.UI.LoginStep;
import steps.car.UI.CarsUiStep;
import utils.DBUtils;
import web.pages.CarsPage;
import web.pages.UserPage;
import web.steps.CommonWebSteps;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;

public class CarsUiTest extends GeneralBasic {
    @Test
    @DisplayName("Сортировка авто по ID")
    @Owner("Trifonov Dmitriy")
    public void sortByCarsID(){
        new CarsUiStep().carsReadAll();
        new CommonWebSteps().clickElement("ID", new CarsPage().buttonIdCars());
        sleep(1000);
        String minIDstr = DBUtils.getMinCarID().replaceAll("\\D+","");
        int minID = Integer.parseInt(minIDstr);
        String idTableCarsStr = new CarsPage().idCarsTable().getText();
        int idTableCars = Integer.parseInt(idTableCarsStr);
        Assertions.assertEquals(idTableCars,minID,"Таблица не отсортированна по минимальному ID");
    }
    @Test
    @DisplayName("Cоздание нового автомобиля")
    @Owner("Trifonov Dmitriy")
    public void createNewCar(){
        LoginStep.authorization();
        new CarsUiStep().createNewCar();
//        sleep(200);
        new UserPage().getNewUserID().shouldBe(visible, Duration.ofSeconds(5));
        String actualTextStatus = new UserPage().getStatus().getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Status: Successfully pushed, code: 201", actualTextStatus, "Ожидаемый текст не соответствует действительному!");
        String actualTextUser = new UserPage().getNewUserID().getText();
        softAssert.assertTrue(actualTextUser.contains("New car ID"), "Поле не содержит: New car ID");
        softAssert.assertAll();
    }
    @Test
    @DisplayName("Cоздание нового автомобиля. 400 статус код")
    @Owner("Glagolev Sergey")
    public void createNewCarWithWrongEngine(){
        LoginStep.authorization();
        new CarsUiStep().createNewCarWithWrongEngine();
        new UserPage().getNewUserID().shouldBe(visible, Duration.ofSeconds(5));
        String actualTextStatus = new UserPage().getStatus().getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Status: AxiosError: Request failed with status code 400", actualTextStatus, "Ожидаемый текст не соответствует действительному!");
    }
}
