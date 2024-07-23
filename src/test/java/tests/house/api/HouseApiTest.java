package tests.house.api;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.house.api.HouseApiStep;
import steps.users.API.UsersApiStep;

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
    @DisplayName("Заселение (создание дома, создание юзера, заселение), API")
    public void settleApi(){
        new UsersApiStep().createUserApi();
        new HouseApiStep().createHouseApi().settleUserApi(UsersApiStep.getUserID());
    }
}
