package tests.car.api;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import steps.car.API.CarAPI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarDeleteAPITest {
    @Test
    public void deleteCarApiTest() {

        CarAPI carCreator = new CarAPI();
        JSONObject response = carCreator.createCarAPI(10, "Diesel", "AUDI", "A5", 100);
        Integer carID = response.getInt("id");

        carCreator.deleteCarApi(carID);

    }
}
