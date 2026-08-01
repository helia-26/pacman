package database;

import java.sql.*;
//کلاس وصل کننده دیتابیس
public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:pacman.db";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}