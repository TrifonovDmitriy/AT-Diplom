package steps.house.ui;

import com.codeborne.selenide.Condition;
import config.classes.MainProps;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import web.pages.HousePage;
import web.pages.UserPage;
import web.steps.CommonWebSteps;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class HouseUiStep {

    @Step("Создание нового дома")
    public int createNewHouse() {
        new CommonWebSteps().clickElement("Houses", new HousePage().housesList())
                .clickElement("Create New", new HousePage().createNewHouse());
        new CommonWebSteps().sendKeys("Floor Count", new HousePage().floorCountField(), "5")
                .sendKeys("Price", new HousePage().priceField(), "1000000")
                .sendKeys("Parking 1", new HousePage().parkingFirstField(), "24")
                .sendKeys("Parking 2", new HousePage().parkingSecondField(), "24")
                .sendKeys("Parking 3", new HousePage().parkingThirdField(), "24")
                .sendKeys("Parking 4", new HousePage().parkingFourthField(), "24")
                .clickElement("Submit", new HousePage().houseSubmitButton());

        // Извлечение сгенерированного ID
        HousePage housePage = new HousePage();
        housePage.generatedHouseId().shouldBe(Condition.visible);
        String newHouseIdStr = housePage.generatedHouseId().getText().replaceAll("\\D+", "");
        int newHouseId = Integer.parseInt(newHouseIdStr);

        return newHouseId;
    }

    @Step("Вселение пользователя")
    public Response settleUser(int houseId, int userId) {
        new CommonWebSteps().clickElement("Houses", new HousePage().housesList())
                .clickElement("Settle or Evict User", new HousePage().settleOrEvictUser())
                .sendKeys("House ID", new HousePage().houseIdField(), String.valueOf(houseId))
                .sendKeys("User ID", new UserPage().fieldEnterUserId(), String.valueOf(userId))
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

    @Step("Удаление дома")
    public void deleteHouse(int houseId) {
        new CommonWebSteps().clickElement("Houses", new HousePage().housesList())
                .clickElement("Read One by ID", new HousePage().readHouseById())
                .sendKeys("House ID", new HousePage().houseIdField(), String.valueOf(houseId))
                .clickElement("Submit", new HousePage().houseSubmitButton())
                .clickElement("Delete House", new HousePage().deleteHouseButton());
    }

    @Step("Чтение информации о домах")
    public void readAllHouses() {
        new CommonWebSteps().clickElement("Houses", new HousePage().housesList())
                .clickElement("Read All", new HousePage().readAllHouses());

        HousePage housePage = new HousePage();
        housePage.housesInfoTable().shouldBe(Condition.visible);

    }

    @Step("Чтение информации о доме по ID")
    public void readHouseById(int houseId) {
        new CommonWebSteps().clickElement("Houses", new HousePage().housesList())
                .clickElement("Read One by ID", new HousePage().readHouseById())
                .sendKeys("House ID", new HousePage().houseInputField(), String.valueOf(houseId))
                .clickElement("Submit", new HousePage().houseSubmitButton());
    }
}
