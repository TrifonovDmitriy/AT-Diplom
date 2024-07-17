package utils;

import config.classes.MainProps;

import java.sql.*;

/**
 * Класс подключения к БД и других методов для работы с БД
 */
public class DBUtils {
    public static Connection driverManager() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
                MainProps.environmentProps.dbUrl(),
                MainProps.loginProps.sqlLogin(),
                MainProps.loginProps.sqlPassword());
    }

    public static String connectToDataBase() {
        String str = null;
        try (Connection connection = driverManager()) {
            str = "Успешное соединение!";
            System.out.println(str);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return str;
    }
}
