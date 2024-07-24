package steps.users.API;

import config.classes.MainProps;
import dto.User;
import io.qameta.allure.Step;
import org.json.JSONObject;
import steps.auth.API.LoginApiStep;
import utils.DBUtils;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class UsersApiStep {
    private int userID;
    public int getUserID() {
        return userID;
    }
    private int amount = 10100100;

    @Step("Создание пользователя API")
    public UsersApiStep createUserApi() {
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
        return this;
    }
    @Step("Удаление пользователя/API")
    public void deleteUserApi(){
        given().when()
                .headers(new LoginApiStep().httpHeaderManager())
                .pathParam("userID", userID)
                .when()
                .delete(MainProps.environmentProps.apiUrl() + "/user/{userID}")
                .then()
                .assertThat().statusCode(204)
                .extract().body().asString();
        DBUtils.getUser(userID);
    }
    @Step("Удаление пользователя/API")
    public void deleteUserApi(int userID){
        given().when()
                .headers(new LoginApiStep().httpHeaderManager())
                .pathParam("userID", userID)
                .when()
                .delete(MainProps.environmentProps.apiUrl() + "/user/{userID}")
                .then()
                .assertThat().statusCode(204)
                .extract().body().asString();
        DBUtils.getUser(userID);
    }

    @Step("Добавление денег пользователю/API")
    public void giveMoneyToUser(){
        String responseAfterGiveMoney = given().when()
                .headers(new LoginApiStep().httpHeaderManager())
                .pathParam("userID", userID)
                .pathParam("money", amount)
                .when()
                .post(MainProps.environmentProps.apiUrl() + "/user/{userID}/money/{money}")
                .then()
                .assertThat().statusCode(200)
                .extract().body().asString();
        JSONObject jsonObject = new JSONObject(responseAfterGiveMoney);
        double amountAfterAdding = jsonObject.getDouble("money");
        Logger.getGlobal().info("Пользователю с ID " + userID + " начислены деньги. Итог: " + amountAfterAdding);
        DBUtils.getAmountAfterAdding(userID);
    }
}
