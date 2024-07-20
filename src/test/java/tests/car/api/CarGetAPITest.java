package tests.car.api;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import steps.car.API.CarAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarGetAPITest {
    @Test
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
}
