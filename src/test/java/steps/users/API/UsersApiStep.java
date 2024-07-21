package steps.users.API;

import config.classes.MainProps;
import dto.User;
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

public class UsersApiStep {
//    private String request = null;
    private int userID;

    //метод создаёт пользователя по заменяемым атрибутам файла json
//    @Step("Создание пользователя API")
//    public void createUserApi(String age, String firstName, String money, String secondName, String sex) {
//        try {
//            request = new String(Files.readAllBytes(Paths.get("src/test/resources/requests/createUser.json")),
//                    StandardCharsets.UTF_8)
//                    .replace("${age}", age)
//                    .replace("${firstName}", firstName)
//                    .replace("${money}", money)
//                    .replace("${secondName}", secondName)
//                    .replace("${sex}", sex);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String response = given().when()
//                .headers(new LoginApiStep().httpHeaderManager())
//                .body(request)
//                .post(MainProps.environmentProps.apiUrl() + "/user")
//                .then()
//                .assertThat().statusCode(201)
//                .extract().body().asString();
//        JSONObject jsonObject = new JSONObject(response);
//        int userID = jsonObject.getInt("id");
//        System.out.println("Создан пользователь с ID: " + userID);
//    }

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
}
