package utils;

import config.classes.MainProps;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.sql.*;
import java.util.HashMap;
import java.util.logging.Logger;

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

    public static HashMap<String, String> selectValueMapFromDataBase(String select) {
        HashMap<String, String> result = new HashMap<>();
        try (Connection connection = driverManager();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(select)) {
            if (resultSet.next()) {
                for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
                    result.put(resultSet.getMetaData().getColumnName(i), resultSet.getString(i));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static HashMap<String, String> getListFromBd(String sqlQuerry) {
        Allure.addAttachment("SqlRequest", sqlQuerry);
        Logger.getGlobal().info(sqlQuerry);
        HashMap<String, String> map = selectValueMapFromDataBase(sqlQuerry);
        if (map != null) {
            Allure.addAttachment("SqlResponse", map.toString());
            Logger.getGlobal().info(map.toString());
        }
        return map;
    }

    @Step("Получение пользователя из базы данных")
    public static String getUser(int userID){
        HashMap<String,String> map =getListFromBd(
                "Select * from public.person where id ='" + userID+"'"
        );
        return map.toString();
    }
}
