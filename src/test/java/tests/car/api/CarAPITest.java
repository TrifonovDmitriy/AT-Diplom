package tests.car.api;


import io.qameta.allure.Owner;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.car.API.CarAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarAPITest {
    @Test
    @DisplayName("Создание авто")
    @Owner("Glagolev Sergey")
    public void createCarApiTest() {

        CarAPI carAPI = new CarAPI();
        JSONObject response = carAPI.createCarAPI(10, "Diesel", "AUDI", "A5", 100);
        assertEquals("Diesel", response.getString("engineType"));
        assertEquals("AUDI", response.getString("mark"));
        assertEquals("A5", response.getString("model"));
        assertEquals(100.0, response.getDouble("price"));

    }

    @Test
    @DisplayName("Удаление авто")
    @Owner("Glagolev Sergey")
    public void deleteCarApiTest() {

        CarAPI carCreator = new CarAPI();
        JSONObject response = carCreator.createCarAPI(10, "Diesel", "AUDI", "A5", 100);
        Integer carID = response.getInt("id");

        carCreator.deleteCarApi(carID);

    }

    @Test
    @DisplayName("Получение авто")
    @Owner("Glagolev Sergey")
    public void getCarApiTest() {

        CarAPI carCreator = new CarAPI();
        JSONObject response = carCreator.createCarAPI(10, "Diesel", "AUDI", "A5", 100);
        Integer carId = response.getInt("id");

        JSONObject responseGet = carCreator.getCarApi(carId);
        assertEquals("Diesel", responseGet.getString("engineType"));
        assertEquals("AUDI", responseGet.getString("mark"));
        assertEquals("A5", responseGet.getString("model"));
        assertEquals(100.0, responseGet.getDouble("price"));
    }

    @Test
    @DisplayName("Изменение авто")
    @Owner("Glagolev Sergey")
    public void updateCarApiTest() throws InterruptedException {

        CarAPI carCreator = new CarAPI();
        JSONObject response = carCreator.createCarAPI(10, "Diesel", "lada", "VESTA", 100);
        Integer carID = response.getInt("id");

        JSONObject responseUpdate = carCreator.updateCarApi(carID, "Electric", "AUDI", "A5", 200);
        assertEquals("Electric", responseUpdate.getString("engineType"));
        assertEquals("AUDI", responseUpdate.getString("mark"));
        assertEquals("A5", responseUpdate.getString("model"));
        assertEquals(200.0, responseUpdate.getDouble("price"));
    }
}
