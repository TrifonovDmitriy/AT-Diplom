package steps.car.API;

import config.classes.MainProps;
import io.qameta.allure.Step;
import org.json.JSONObject;
import steps.auth.API.LoginApiStep;
import utils.DBUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

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
        JSONObject jsonObject =new JSONObject(response);
        Integer carID = jsonObject.getInt("id");
        Logger.getGlobal().info("Создан автомобиль с ID: " + carID);
        DBUtils.getCar(carID);
        return jsonObject;
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
        Logger.getGlobal().info("Удален автомобиль с ID: " + carID);
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
        JSONObject jsonObject =new JSONObject(response);
        Integer carIDFromResponse = jsonObject.getInt("id");
        Logger.getGlobal().info("Получен автомобиль с ID: " + carIDFromResponse);
        DBUtils.getCar(carIDFromResponse);
        return jsonObject;
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
        JSONObject jsonObject =new JSONObject(response);
        Integer newCarID = jsonObject.getInt("id");
        Logger.getGlobal().info("Изменен автомобиль с ID: " + newCarID);
        DBUtils.getCar(newCarID);
        return jsonObject;


    }
    }
