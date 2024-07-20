package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static utils.DBUtils.connectToDataBase;

public class SimpleTest {

    @Test
    @DisplayName("Соединение с БД")
    public void sqlConnect() {
        Assertions.assertEquals("Успешное соединение!", connectToDataBase());
    }
}
