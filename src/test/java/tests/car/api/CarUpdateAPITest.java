package tests.car.api;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import steps.car.API.CarAPI;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarUpdateAPITest {
    @Test
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

