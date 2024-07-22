package web.pages;

import com.codeborne.selenide.SelenideElement;
import web.utils.XPathBuilder;

public class HousePage {

    public SelenideElement housesList() {
        return XPathBuilder.containsElement("a", "text()", "Houses");
    }

    public SelenideElement createNewHouse() {
        return XPathBuilder.containsElement("a", "text()", "Create new");
    }

    public SelenideElement floorCountField() {
        return XPathBuilder.element("input", "id", "floor_send");
    }

    public SelenideElement priceField() {
        return XPathBuilder.element("input", "id", "price_send");
    }

    public SelenideElement parkingFirstField() {
        return XPathBuilder.element("input", "id", "parking_first_send");
    }

    public SelenideElement parkingSecondField() {
        return XPathBuilder.element("input", "id", "parking_second_send");
    }

    public SelenideElement parkingThirdField() {
        return XPathBuilder.element("input", "id", "parking_third_send");
    }

    public SelenideElement parkingFourthField() {
        return XPathBuilder.element("input", "id", "parking_fourth_send");
    }

    public SelenideElement houseSubmitButton() {
        return XPathBuilder.containsElement("button", "text()", "Submit");
    }

    public SelenideElement successMessage() {
        return XPathBuilder.containsElement("button", "text()", "Status: Successfully pushed, code: 201");
    }

    public SelenideElement successMessageEvict() {
        return XPathBuilder.containsElement("button", "text()", "Status: Successfully pushed, code: 200");
    }

    public SelenideElement generatedHouseId() {
        return XPathBuilder.containsElement("button", "text()", "New house ID: ");
    }

    public SelenideElement settleOrEvictUser() {
        return XPathBuilder.containsElement("a", "text()", "Settle or evict user");
    }

    public SelenideElement houseIdField() {
        return XPathBuilder.element("input", "id", "house_id");
    }

    public SelenideElement userIdField() {
        return XPathBuilder.element("input", "id", "userId");
    }

    public SelenideElement radioButtonSettle() {
        return XPathBuilder.numberElement("input", "type", "radio", "1");
    }

    public SelenideElement radioButtonEvict() {
        return XPathBuilder.numberElement("input", "type", "radio", "2");
    }

    public SelenideElement deleteHouseButton() {
        return XPathBuilder.containsElement("button", "text()", "Delete House");
    }

    public SelenideElement readAllHouses() {
        return XPathBuilder.containsElement("a", "text()", "Read all");
    }

    public SelenideElement readHouseById() {
        return XPathBuilder.containsElement("a", "text()", "Read one by ID");
    }

    public SelenideElement houseInfoTable() {
        return XPathBuilder.containsElementInclude("table", "class", "table table-bordered", "/tbody/tr");
    }

    public SelenideElement lodgersTable() {
        return XPathBuilder.containsElementInclude("table", "class", "tableLodgers table", "/tbody/tr");
    }

    public SelenideElement parkingsTable() {
        return XPathBuilder.containsElementInclude("table", "class", "tableParkings table", "/tbody/tr");
    }
}
