package steps.auth.API;

import config.classes.MainProps;
import io.qameta.allure.Step;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Класс авторизации через API
 */
public class LoginApiStep {
    private String request = null;
    private String token = null;

    @Step("Авторизация API")
    public String authorizationAPI(String userName, String password) {
        try {
            request = new String(Files.readAllBytes(Paths.get("src/test/resources/requests/login.json")),
                    StandardCharsets.UTF_8)
                    .replace("${password}", password)
                    .replace("${userName}", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String response = given().when()
                .header("Content-Type", "application/json;charset=utf-8")
                .body(request)
                .post(MainProps.environmentProps.apiUrl() + "/login")
                .then()
                .assertThat().statusCode(202)
                .extract().body().asString();
        JSONObject jsonObject = new JSONObject(response);
        token = jsonObject.getString("access_token");
        return token;
    }

    public Headers httpHeaderManager() {
        Header contentType = new Header("Content-Type", "application/json;charset=utf-8");
        Header authorization = new Header("Authorization", "Bearer " + new LoginApiStep().authorizationAPI(MainProps.loginProps.apiLogin(), MainProps.loginProps.apiPassword()));
        List<Header> headerList = new ArrayList<>();
        headerList.add(contentType);
        headerList.add(authorization);
        return new Headers(headerList);
    }
}
