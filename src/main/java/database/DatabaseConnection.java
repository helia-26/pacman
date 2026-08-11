package database;

import java.sql.*;
//کلاس وصل کننده دیتابیس
public class DatabaseConnection {
    //آدرس دیتابیس
    private static final String URL = "jdbc:sqlite:pacman.db";
    //متد استاتیک که اتصال به دیتابیس رو برمیگردونه
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}