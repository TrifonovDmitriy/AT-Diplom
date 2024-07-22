package steps.house.api;

import config.classes.MainProps;
import dto.House;
import dto.ParkingPlace;
import dto.User;
import io.qameta.allure.Step;
import org.json.JSONObject;
import steps.auth.API.LoginApiStep;
import utils.DBUtils;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import static io.restassured.RestAssured.given;


public class HouseApiStep {
    private int houseID;
    private int userID;
    @Step("Создание дома, API")
    public HouseApiStep createHouseApi() {
        ParkingPlace parkingPlaceOne = ParkingPlace.builder()
                .id(10)
                .isCovered(true)
                .isWarm(true)
                .placesCount(14)
                .build();
        ParkingPlace parkingPlaceTwo = ParkingPlace.builder()
                .id(10)
                .isCovered(false)
                .isWarm(false)
                .placesCount(33)
                .build();
        House house = House.builder()
                .floorCount(14)
                .id(10)
                .parkingPlaces(List.of(parkingPlaceOne, parkingPlaceTwo))
                .price(2000)
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
    public void settleUserApi(){
        User user = User.builder()
                .age(25)
                .firstName("Michael")
                .id(10)
                .money(155000)
                .secondName("Kubikov")
                .sex("MALE")
                .build();
        String userJson = user.toJson();
        String response = given().when()
                .headers(new LoginApiStep().httpHeaderManager())
                .body(userJson)
                .post(MainProps.environmentProps.apiUrl() + "/user")
                .then()
                .assertThat().statusCode(201)
                .extract().body().asString();
        JSONObject jsonObject = new JSONObject(response);
        userID = jsonObject.getInt("id");
        Logger.getGlobal().info("Создан пользователь с ID: " + userID);
        DBUtils.getUser(userID);

    given().when()
                .headers(new LoginApiStep().httpHeaderManager())
                .pathParam("houseID", houseID)
                .pathParam("userID", userID)
                .when()
                .post(MainProps.environmentProps.apiUrl() + "/house/{houseID}/settle/{userID}")
                .then()
                .assertThat().statusCode(200)
                .extract().body().asString();
        Logger.getGlobal().info("Пользователь с ID = " + userID + " заселен в дом " + houseID);
        DBUtils.getUser(userID);
    }
}

