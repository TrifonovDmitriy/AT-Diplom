package steps.car.API;

import config.classes.MainProps;
import io.qameta.allure.Step;
import org.json.JSONObject;
import steps.auth.API.LoginApiStep;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class CarAPI {


    @Step("Создание авто API")
    public JSONObject createCarAPI(int id, String engineType, String mark, String model, double price) {
        String request;
        try {
            request = new String(Files.readAllBytes(Paths.get("src/test/resources/requests/car.json")),
                    StandardCharsets.UTF_8)
                    .replace("${id}", String.valueOf(id))
                    .replace("${engineType}", engineType)
                    .replace("${mark}", mark)
                    .replace("${model}", model)
                    .replace("${price}", String.valueOf(price));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String response = given().when()
                // .header("Content-Type", "application/json;charset=utf-8")
                .headers(new LoginApiStep().httpHeaderManager())
                .body(request)
                .post(MainProps.environmentProps.apiUrl() + "/car")
                .then()
                .assertThat().statusCode(201)
                .extract().body().asString();
        return new JSONObject(response);

    }

    @Step("Удаление авто/API")
    public void deleteCarApi(int carID) {

        given().when()
                .headers(new LoginApiStep().httpHeaderManager())
                .pathParam("carID", carID)
                .when()
                .delete(MainProps.environmentProps.apiUrl() + "/car/{carID}")
                .then()
                .assertThat().statusCode(204)
                .extract().body().asString();
    }

    @Step("Получение авто/API")
    public JSONObject getCarApi(int carID) {

        String response = given().when()
                .headers(new LoginApiStep().httpHeaderManager())
                .pathParam("carID", carID)
                .when()
                .get(MainProps.environmentProps.apiUrl() + "/car/{carID}")
                .then()
                .assertThat().statusCode(200)
                .extract().body().asString();
        return new JSONObject(response);
    }

    @Step("Изменение авто/API")
    public JSONObject updateCarApi(int carID, String engineType, String mark, String model, double price) {

        String request;
        try {
            request = new String(Files.readAllBytes(Paths.get("src/test/resources/requests/car.json")),
                    StandardCharsets.UTF_8)
                    .replace("${id}", String.valueOf(carID))
                    .replace("${engineType}", engineType)
                    .replace("${mark}", mark)
                    .replace("${model}", model)
                    .replace("${price}", String.valueOf(price));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String response = given().when()
                .headers(new LoginApiStep().httpHeaderManager())
                .pathParam("carID", carID)
                .body(request)
                .put(MainProps.environmentProps.apiUrl() + "/car/{carID}")
                .then()
                .assertThat().statusCode(202)
                .extract().body().asString();
        return new JSONObject(response);


    }
    }
