package tests.car.api;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import steps.auth.API.LoginApiStep;
import steps.car.API.CreateCarAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarAPI {
    @Test
    public void createCarApi() {
        //LoginApiStep auth =new LoginApiStep();
        //auth.authorizationAPI("user@pflb.ru", "user");

        CreateCarAPI carCreator = new CreateCarAPI();
        JSONObject response = carCreator.createCarAPI(10, "Diesel", "lada", "Vesta", 100);
        assertEquals("Diesel", response.getString("engineType"));
    }
}
