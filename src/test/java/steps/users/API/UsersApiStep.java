package steps.users.API;

import config.classes.MainProps;
import io.qameta.allure.Step;
import org.json.JSONObject;
import steps.auth.API.LoginApiStep;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class UsersApiStep {
    private String request=null;
    @Step("Создание пользователя API")
    public void createUserApi(String firstName, String secondName, String sex){
        try{
            request = new String(Files.readAllBytes(Paths.get("src/test/resources/requests/createUser.json")),
                    StandardCharsets.UTF_8)
//                    .replace("${age}", age)
                    .replace("${firstName}", firstName)
//                    .replace("${money}", money)
                    .replace("${secondName}",secondName)
                    .replace("${sex}", sex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String response = given().when()
                .headers(new LoginApiStep().httpHeaderManager())
                .body(request)
                .post(MainProps.environmentProps.apiUrl() +"/user")
                .then()
                .assertThat().statusCode(201)
                .extract().body().asString();
        JSONObject jsonObject = new JSONObject(response);
        int userID = jsonObject.getInt("id");
        System.out.println("Создан пользователь с ID: " + userID);
    }
}
