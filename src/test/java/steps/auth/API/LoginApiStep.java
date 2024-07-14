package steps.auth.API;

import io.qameta.allure.Step;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class LoginApiStep {
    private String request = null;

    @Step("Авторизация API")
    public void authorizationAPI(String userName, String password) {
        try {
            request = new String(Files.readAllBytes(Paths.get("src/test/resources/requests/login.json")),
                    StandardCharsets.UTF_8)
                    .replace("${password}", password)
                    .replace("${userName}", userName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        given().when()
                .header("Content-Type", "application/json;charset=utf-8")
                .body(request)
                .post("http://77.50.236.203:4879/login")
                .then()
                .assertThat().statusCode(202)
                .extract().body().asString();
    }
}
