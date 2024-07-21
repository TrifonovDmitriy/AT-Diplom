package tests.cars.UI;

import base.GeneralBasic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.cars.UI.CarsUiStep;
import utils.DBUtils;
import web.pages.CarsPage;
import web.steps.CommonWebSteps;

import static com.codeborne.selenide.Selenide.sleep;

public class CarsTest extends GeneralBasic {
    @Test
    @DisplayName("Сортировка авто по ID")
    @Owner("Trifonov Dmitriy")
    public void sortByCarsID(){
        new CarsUiStep().carsReadAll();
        sleep(2000);
        new CommonWebSteps().clickElement("Click ID", new CarsPage().buttonIdCars());
        sleep(2000);
        String minIDstr = DBUtils.getMinCarID().replaceAll("\\D+","");
        int minID = Integer.parseInt(minIDstr);
        String idTableCarsStr = new CarsPage().idCarsTable().getText();
        int idTableCars = Integer.parseInt(idTableCarsStr);
        Assertions.assertEquals(idTableCars,minID,"Таблица не отсортированна по минимальному ID");
    }
}
