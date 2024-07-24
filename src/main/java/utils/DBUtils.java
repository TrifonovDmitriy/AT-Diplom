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
    @Step("Получение минимального ID автомобиля")
    public static String getMinCarID(){
        HashMap<String,String> map =getListFromBd(
                "Select MIN(id) from public.car"
        );
        return map.toString();
    }
    @Step("Получение суммы денег из БД после добавления")
    public static String getAmountAfterAdding(int userID){
        HashMap<String,String> map =getListFromBd(
                "Select money from public.person where id = '"+ userID +"'"
        );
        return map.toString();
    }
    @Step("Получение информации о доме из БД")
    public static String getHouse(int houseId){
        HashMap<String,String> map = getListFromBd(
                "Select * from public.house where id ='" + houseId + "'"
        );
        return map.toString();
    }
    @Step("Получение информации о парковочных местах из БД")
    public static String getParkingPlaces(int houseId){
        HashMap<String,String> map = getListValuesFromBd(
                "Select * from public.parking_place where house_id ='" + houseId +"'"
        );
        return map.toString();
    }
    @Step("Получение автомобиля из базы данных")
    public static String getCar(int carID){
        HashMap<String,String> map =getListFromBd(
                "Select * from public.car where id ='" + carID+"'"
        );
        return map.toString();
    }

    @Step("Проверка создания дома в базе данных")
    public static HashMap<String, String> getHouseData(int houseId) {
        return getListFromBd("Select * from public.house where id ='" + houseId + "'");
    }

    @Step("Проверка информации о жильцах в базе данных")
    public static HashMap<String, String>  getLodgerData(int userId) {
        return getListFromBd(
                "Select house_id from public.person where id ='" + userId + "'"
        );
    }


    public static HashMap<String, String> getListValuesFromBd(String sqlQuerry) {
        Allure.addAttachment("SqlRequest", sqlQuerry);
        Logger.getGlobal().info(sqlQuerry);
        HashMap<String, String> map = DBUtils.selectFewValueMapFromDataBase(sqlQuerry);
        if (map != null) {
            Allure.addAttachment("SqlResponse", map.toString());
            Logger.getGlobal().info(map.toString());
        }
        return map;
    }

    public static HashMap<String, String> selectFewValueMapFromDataBase(String select) {
        HashMap<String, String> result = new HashMap<>();
        try (Connection connection = driverManager();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(select)) {
            int i = 0;
            while (resultSet.next()) {
                result.put(resultSet.getMetaData().getColumnName(1) + "_" + i, resultSet.getString(1));
                i++;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
