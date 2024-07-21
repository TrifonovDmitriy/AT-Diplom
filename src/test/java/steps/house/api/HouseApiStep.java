package steps.house.api;

import config.classes.MainProps;
import dto.House;
import dto.ParkingPlace;
import io.qameta.allure.Step;
import org.json.JSONObject;
import steps.auth.API.LoginApiStep;
import utils.DBUtils;

import java.util.Collections;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class HouseApiStep {
    private int houseID;
    private int userID;
    @Step("Создание дома, API")
    public HouseApiStep createHouseApi() {
        ParkingPlace parkingPlace = ParkingPlace.builder()
                .id(10)
                .isCovered(true)
                .isWarm(true)
                .placesCount(28)
                .build();
        House house = House.builder()
                .floorCount(14)
                .id(10)
                .parkingPlaces(Collections.singletonList(parkingPlace))
                .price(8000111)
                .build();
        String houseJson = house.toJson();
        String response = given().when()
                .headers(new LoginApiStep().httpHeaderManager())
                .body(houseJson)
                .post(MainProps.environmentProps.apiUrl() + "/house")
                .then()
                .assertThat().statusCode(201)
                .extract().body().asString();
        JSONObject jsonObject = new JSONObject(response);
        houseID = jsonObject.getInt("id");
        Logger.getGlobal().info("Создан дом с ID: " + houseID);
        DBUtils.getHouse(houseID);
        DBUtils.getParkingPlaces(houseID);
        return this;
    }

    @Step("Изменение дома, API")
    public void changeHouseApi(){
        ParkingPlace parkingPlace = ParkingPlace.builder()
                .id(houseID)
                .isCovered(false)
                .isWarm(false)
                .placesCount(2)
                .build();
        House house = House.builder()
                .floorCount(14)
                .id(houseID)
                .parkingPlaces(Collections.singletonList(parkingPlace))
                .price(8888888)
                .build();
        String houseJson = house.toJson();

        String responce = given()
                .headers(new LoginApiStep().httpHeaderManager())
                .pathParams("houseID", houseID)
                .body(houseJson)
                .when()
                .put(MainProps.environmentProps.apiUrl() + "/house/{houseID}")
                .then()
                .assertThat().statusCode(202)
                .extract().body().asString();
        System.out.println(responce);
    }

    @Step("Заселение, API")
    public void settleApi(){
        String responseAfterSettle = given().when()
                .headers(new LoginApiStep().httpHeaderManager())
                .pathParam("userID", userID)
                .pathParam("houseID", houseID)
                .when()
                .post(MainProps.environmentProps.apiUrl() + "/house/{houseID}/settle/{userID}")
                .then()
                .assertThat().statusCode(200)
                .extract().body().asString();
        JSONObject jsonObject = new JSONObject(responseAfterGiveMoney);
        double amountAfterAdding = jsonObject.getDouble("money");
        Logger.getGlobal().info("Пользователь с ID = " + userID + " заселен в дом " + amountAfterAdding);
        DBUtils.getAmountAfterAdding(userID);

    }







}

