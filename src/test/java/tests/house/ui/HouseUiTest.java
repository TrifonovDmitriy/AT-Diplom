package tests.house.ui;

import base.GeneralBasic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.auth.UI.LoginStep;
import steps.house.ui.HouseUiStep;
import utils.DBUtils;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class HouseUiTest extends GeneralBasic {

    private static int houseId;

    @BeforeAll
    public static void setUp() {
        LoginStep.authorization();

        HouseUiStep houseUiStep = new HouseUiStep();
        houseId = houseUiStep.createNewHouse();
        // Проверка базы данных
        HashMap<String, String> houseData = DBUtils.getHouseData(houseId);
        assertNotNull(houseData, "Дом не создан");
//        assertEquals(String.valueOf(expectedFloorCount), houseData.get("floor_count"), "Количество этажейне соответствует");
//        assertEquals(String.valueOf(expectedPrice), houseData.get("price"), "Неверная стоимость");
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
        houseUiStep.readHouseById(houseId);
    }


    @Test
    @DisplayName("Создание нового дома")
    @Owner("Rustam Dzhafarov")
    public void createNewHouseTest() {
        // Тест создан для setup
    }

    @Test
    @DisplayName("Вселение пользователя в дом")
    @Owner("Rustam Dzhafarov")
    public void settleUserTest() {
        LoginStep.authorization();

        int userId = 5; // Идентификатор пользователя

        HouseUiStep houseUiStep = new HouseUiStep();
        houseUiStep.settleUser(houseId, userId);

        // Проверка базы данных
        HashMap<String, String> lodgerData = DBUtils.getLodgerData(userId);
        assertEquals(String.valueOf(houseId), lodgerData.get("house_id"), "Данные о арендаторе не должны быть нулевыми.");
    }

    @Test
    @DisplayName("Выселение пользователя из дома")
    @Owner("Rustam Dzhafarov")
    public void evictUserTest() {
        LoginStep.authorization();

        int userId = 5; // Идентификатор пользователя

        HouseUiStep houseUiStep = new HouseUiStep();
        houseUiStep.evictUser(houseId, userId);

        // Проверка базы данных
        HashMap<String, String> lodgerData = DBUtils.getLodgerData(userId);
        assertEquals(String.valueOf(houseId), lodgerData.get("house_id"), "Данные о арендаторе должны быть нулевыми.");
    }

    @Test
    @DisplayName("Удаление дома")
    @Owner("Rustam Dzhafarov")
    public void deleteHouseTest() {
        LoginStep.authorization();

        HouseUiStep houseUiStep = new HouseUiStep();
        houseUiStep.deleteHouse(houseId);

        // Проверка базы данных
        HashMap<String, String> houseData = DBUtils.getHouseData(houseId);
        assertTrue(houseData.isEmpty(), "Данные о доме должны быть пустыми после удаления.");
    }

}
