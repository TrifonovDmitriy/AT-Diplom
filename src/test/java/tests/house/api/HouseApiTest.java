package tests.house.api;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.house.api.HouseApiStep;

public class HouseApiTest {

    @Test
    @Owner("Lozhkina Elena")
    @DisplayName("Создание дома, API")
    public void houseCreateApi(){
        new HouseApiStep().createHouseApi();
    }

    @Test
    @Owner("Lozhkina Elena")
    @DisplayName("Изменение дома, API")
    public void houseChangeApi(){
        new HouseApiStep().createHouseApi().changeHouseApi();
    }

    @Test
    @Owner("Lozhkina Elena")
    @DisplayName("Заселение пользователя в дом, API")
    public void settleApi(){
        new HouseApiStep().createHouseApi().changeHouseApi();
    }


}
