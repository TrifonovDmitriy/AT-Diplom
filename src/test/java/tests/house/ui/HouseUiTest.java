package tests.house.ui;

import base.GeneralBasic;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;
import steps.auth.UI.LoginStep;
import steps.house.ui.HouseUiStep;
import steps.users.UI.UsersUIStep;
import utils.DBUtils;
import web.pages.UserPage;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class HouseUiTest extends GeneralBasic {

    @Test
    @DisplayName("Создание нового дома")
    @Owner("Rustam Dzhafarov")
    public void createNewHouseTest() {
        LoginStep.authorization();

        HouseUiStep houseUiStep = new HouseUiStep();
        int houseId = houseUiStep.createNewHouse();

        // Проверка базы данных
        DBUtils.getHouse(houseId);
        DBUtils.getParkingPlaces(houseId);
    }

    @Test
    @DisplayName("Вселение пользователя в дом")
    @Owner("Rustam Dzhafarov")
    public void settleUserTest() {
        LoginStep.authorization();

         // Создание нового дома
        HouseUiStep houseUiStep = new HouseUiStep();
        int houseId = houseUiStep.createNewHouse();

        // Создание нового пользователя
        UsersUIStep.createUserUi();
        String actualTextUser = new UserPage().getNewUserID().getText();
        String userIdStr = actualTextUser.replaceAll("\\D+", "");
        int userId = Integer.parseInt(userIdStr);


        // Вселение пользователя
        Response response = houseUiStep.settleUser(houseId, userId);

        // Проверка статус кода
        int statusCode = response.getStatusCode();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200, "Expected status code 200");

        // Проверка базы данных
        HashMap<String, String> lodgerData = DBUtils.getLodgerData(userId);
        assertEquals(String.valueOf(houseId), lodgerData.get("house_id"), "Данные о арендаторе не должны быть нулевыми.");
    }


    @Test
    @DisplayName("Выселение пользователя из дома")
    @Owner("Rustam Dzhafarov")
    public void evictUserTest() {
        LoginStep.authorization();

        // Создание нового дома
        HouseUiStep houseUiStep = new HouseUiStep();
        int houseId = houseUiStep.createNewHouse();

        // Создание нового пользователя
        UsersUIStep.createUserUi();
        String actualTextUser = new UserPage().getNewUserID().getText();
        String userIdStr = actualTextUser.replaceAll("\\D+", "");
        int userId = Integer.parseInt(userIdStr);


        // Вселение пользователя
        houseUiStep.settleUser(houseId, userId);

        // Выселение пользователя
        Response response = houseUiStep.evictUser(houseId, userId);

        // Проверка статус кода
        int statusCode = response.getStatusCode();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200, "Expected status code 200");

        // Проверка базы данных
        HashMap<String, String> lodgerData = DBUtils.getLodgerData(userId);
        assertNull(lodgerData.get("house_id"), "Данные о арендаторе должны быть нулевыми.");
    }

    @Test
    @DisplayName("Удаление дома")
    @Owner("Rustam Dzhafarov")
    public void deleteHouseTest() {
        LoginStep.authorization();

        HouseUiStep houseUiStep = new HouseUiStep();
        int houseId = houseUiStep.createNewHouse();
        houseUiStep.deleteHouse(houseId);

        // Проверка базы данных
        HashMap<String, String> houseData = DBUtils.getHouseData(houseId);
        assertTrue(houseData.isEmpty(), "Данные о доме должны быть пустыми после удаления.");
    }

    @Test
    @DisplayName("Чтение информации о домах в UI")
    @Owner("Rustam Dzhafarov")
    public void readAllHousesTest() {
        LoginStep.authorization();

        HouseUiStep houseUiStep = new HouseUiStep();
        houseUiStep.readAllHouses();

    }

    @Test
    @DisplayName("Чтение информации о доме по ID в UI")
    @Owner("Rustam Dzhafarov")
    public void readHouseByIdTest() {
        LoginStep.authorization();

        HouseUiStep houseUiStep = new HouseUiStep();
        int houseId = houseUiStep.createNewHouse();

        Response response = houseUiStep.readHouseById(houseId);


        int statusCode = response.getStatusCode();
        Assertions.assertEquals(200, statusCode, "Проверка статус кода");

        String responseBody = response.getBody().asString();
        Assertions.assertFalse(responseBody.isEmpty(), "Проверка, что тело ответа не пустое");
    }


}
