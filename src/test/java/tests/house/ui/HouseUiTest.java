package tests.house.ui;

import base.GeneralBasic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.house.ui.HouseUiStep;
import utils.DBUtils;

public class HouseUiTest extends GeneralBasic {

    private static int houseId;

    @BeforeAll
    public static void setUp() {
        HouseUiStep houseUiStep = new HouseUiStep();
        houseId = houseUiStep.createNewHouse();
        // Проверка базы данных
        DBUtils.assertHouseData(houseId, 5, 1000000);
    }

    @Test
    @DisplayName("Чтение информации о домах в UI")
    @Owner("Rustam Dzhafarov")
    public void readAllHousesTest() {
        HouseUiStep houseUiStep = new HouseUiStep();
        houseUiStep.readAllHouses();
    }

    @Test
    @DisplayName("Чтение информации о доме по ID в UI")
    @Owner("Rustam Dzhafarov")
    public void readHouseByIdTest() {
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
        int userId = 5; // Идентификатор пользователя

        HouseUiStep houseUiStep = new HouseUiStep();
        houseUiStep.settleUser(houseId, userId);

        // Проверка базы данных
        DBUtils.assertLodgerData(houseId, userId, true);
    }

    @Test
    @DisplayName("Выселение пользователя из дома")
    @Owner("Rustam Dzhafarov")
    public void evictUserTest() {
        int userId = 5; // Идентификатор пользователя

        HouseUiStep houseUiStep = new HouseUiStep();
        houseUiStep.evictUser(houseId, userId);

        // Проверка базы данных
        DBUtils.assertLodgerData(houseId, userId, false);
    }

    @Test
    @DisplayName("Удаление дома")
    @Owner("Rustam Dzhafarov")
    public void deleteHouseTest() {
        HouseUiStep houseUiStep = new HouseUiStep();
        houseUiStep.deleteHouse(houseId);

        // Проверка базы данных
        DBUtils.assertHouseDeleted(houseId);
    }

}
