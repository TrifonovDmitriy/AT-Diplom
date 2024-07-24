package steps.house.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import config.classes.MainProps;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import web.pages.HousePage;
import web.pages.UserPage;
import web.steps.CommonWebSteps;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.refresh;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class HouseUiStep {

    @Step("Создание нового дома")
    public int createNewHouse() {
        new CommonWebSteps().clickElement("Houses", new HousePage().housesList())
                .clickElement("Create New", new HousePage().createNewHouse());
        new CommonWebSteps().sendKeys("Floor Count", new HousePage().floorCountField(), "4")
                .sendKeys("Price", new HousePage().priceField(), "100000")
                .sendKeys("Parking 1", new HousePage().parkingFirstField(), "2")
                .sendKeys("Parking 2", new HousePage().parkingSecondField(), "2")
                .sendKeys("Parking 3", new HousePage().parkingThirdField(), "2")
                .sendKeys("Parking 4", new HousePage().parkingFourthField(), "2")
                .clickElement("Submit", new HousePage().houseSubmitButton());

        // Извлечение сгенерированного ID
        HousePage housePage = new HousePage();
//        sleep(1000);

        housePage.generatedHouseId().shouldBe(Condition.visible);
        String newHouseIdStr = housePage.generatedHouseId().getText().replaceAll("\\D+", "");
        int newHouseId = Integer.parseInt(newHouseIdStr);

        return newHouseId;
    }

    @Step("Вселение пользователя")
    public Response settleUser(int houseId, int userId) {
        new CommonWebSteps().clickElement("Houses", new HousePage().housesList())
                .clickElement("Settle or Evict User", new HousePage().settleOrEvictUser())
                .sendKeys("User ID", new UserPage().fieldEnterUserId(), String.valueOf(userId))
                .sendKeys("House ID", new HousePage().houseIdField(), String.valueOf(houseId))
                .clickElement("Radio Button", new HousePage().radioButtonSettle())
                .clickElement("Submit", new HousePage().houseSubmitButton());

        String apiUrl = MainProps.environmentProps.apiUrl();

        return given().when()
                .post(apiUrl + "/house/" + houseId + "/settle/" + userId)
                .then().log().all()
                .extract().response();

    }

    @Step("Выселение пользователя")
    public Response evictUser(int houseId, int userId) {
        refresh();
        new CommonWebSteps().clickElement("Houses", new HousePage().housesList())
                .clickElement("Settle or Evict User", new HousePage().settleOrEvictUser())
                .sendKeys("House ID", new HousePage().houseIdField(), String.valueOf(houseId))
                .sendKeys("User ID", new UserPage().fieldEnterUserId(), String.valueOf(userId))
                .clickElement("Radio Button", new HousePage().radioButtonEvict())
                .clickElement("Submit", new HousePage().houseSubmitButton());

        String apiUrl = MainProps.environmentProps.apiUrl();

        return given().when()
                .post(apiUrl + "/house/" + houseId + "/evict/" + userId)
                .then().log().all()
                .extract().response();
    }

    public void deleteHouse(int houseId) {
        new CommonWebSteps().clickElement("All DELETE", new HousePage().allDeleteLink());

        Selenide.switchTo().window(1);

        new CommonWebSteps()
                .sendKeys("House ID", new HousePage().houseInputFieldDel(), String.valueOf(houseId))
                .clickElement("DELETE HOUSE", new HousePage().deleteHouseButton());
        // Проверка успешного удаления
        HousePage housePage = new HousePage();
        housePage.successMessage204().shouldBe(Condition.visible);

        Selenide.closeWindow();
        Selenide.switchTo().window(0);
    }

    @Step("Чтение информации о домах")
    public void readAllHouses() {
        HousePage housePage = new HousePage();
        new CommonWebSteps().clickElement("Houses", housePage.housesList())
                .clickElement("Read All", new HousePage().readAllHouses());

        housePage.housesPage().shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Step("Чтение информации о доме по ID")
    public Response readHouseById(int houseId) {
        new CommonWebSteps().clickElement("Houses", new HousePage().housesList())
                .clickElement("Read One by ID", new HousePage().readHouseById())
                .sendKeys("House ID", new HousePage().houseInputField(), String.valueOf(houseId))
                .clickElement("Read", new HousePage().houseSubmitButton());

        String apiUrl = MainProps.environmentProps.apiUrl();

        return given()
                .when()
                .get(apiUrl + "/house/" + houseId)
                .then().log().all()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();
    }
}
