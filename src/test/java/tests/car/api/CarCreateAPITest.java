package tests.car.api;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import steps.car.API.CarAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarCreateAPITest {
    @Test
    public void createCarApiTest() {

        CarAPI carAPI = new CarAPI();
        JSONObject response = carAPI.createCarAPI(10, "Diesel", "AUDI", "A5", 100);
        assertEquals("Diesel", response.getString("engineType"));
        assertEquals("AUDI", response.getString("mark"));
        assertEquals("A5", response.getString("model"));
        assertEquals(100.0, response.getDouble("price"));

    }
}
