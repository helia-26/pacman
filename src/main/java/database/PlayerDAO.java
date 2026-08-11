package database;

import java.sql.*;
//کلاس اصلی درست کننده دیتابیس
//Data Access Object
public class PlayerDAO {
    //ساخت جدول
    public void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS players (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL UNIQUE, " +
                        "high_score INTEGER NOT NULL DEFAULT 0" + ")";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.executeUpdate();

            System.out.println("Players table is ready.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePlayer(String name) {

        String sql = "INSERT OR IGNORE INTO players(name, high_score) " + "VALUES (?, 0)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.executeUpdate();

            System.out.println("Player saved: " + name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getHighScore(String name) {

        String sql = "SELECT high_score FROM players " + "WHERE name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getInt("high_score");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void updateHighScore(String name, int score) {

        String sql = "UPDATE players " + "SET high_score = ? " + "WHERE name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, score);
            statement.setString(2, name);
            statement.executeUpdate();

            System.out.println("High score updated: " + score);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}