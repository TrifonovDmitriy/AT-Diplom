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
        return XPathBuilder.containsElement("button", "@class", "tableButton btn");
    }

    public SelenideElement generatedHouseId() {
        return XPathBuilder.containsElement("button", "text()", "New house ID: ");
    }

    public SelenideElement housesPage(){
        return XPathBuilder.elementText("button","text","Reload");
    }

    public SelenideElement settleOrEvictUser() {
        return XPathBuilder.containsElement("a", "text()", "Settle or evict user");
    }

    public SelenideElement houseIdField() {

        return XPathBuilder.element("input", "id", "house_send");
    }

    public SelenideElement radioButtonSettle() {

        return XPathBuilder.element("input", "value", "settle");
    }

    public SelenideElement radioButtonEvict() {

        return XPathBuilder.element("input", "value", "evict");
    }

    public SelenideElement readAllHouses() {

        return XPathBuilder.containsElement("a", "text()", "Read all");
    }

    public SelenideElement readHouseById() {

        return XPathBuilder.containsElement("a", "text()", "Read one by ID");
    }

    public SelenideElement houseInputField() {
        return XPathBuilder.element("input", "id", "house_input");
    }
    public SelenideElement allDeleteLink() {
        return XPathBuilder.containsElement("a", "text()", "All DELETE");
    }

    public SelenideElement successMessage204() {
        return XPathBuilder.containsElementWithIndex("button", "@class", "status btn", 2);
    }

    public SelenideElement houseInputFieldDel() {
        return XPathBuilder.elementWithIndex("input", "id", "house_input", 2);
    }

    public SelenideElement deleteHouseButton() {
        return XPathBuilder.element("button", "value", "house");
    }
}
