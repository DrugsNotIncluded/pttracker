package com.example.examplemod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.UUID;

public class DatabaseManager {
    private Connection connection;
    private String dbUrl;

    public DatabaseManager(String host, String dbName, String user, String password) {
        this.dbUrl = "jdbc:postgresql://" + host + "/" + dbName + "?user=" + user + "&password=" + password;
        initializeDatabase();
    }

    private void createTables() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            // Таблица игроков
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS playtime (" +
                            "uuid UUID PRIMARY KEY, " +
                            "play_time BIGINT" +
                            ")"
            );
        }
    }


    private void initializeDatabase() {
        try {
            connection = DriverManager.getConnection(dbUrl);
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void WriteDB(UUID uuid, Duration time) {};

    public UUID getTimeByUUID(UUID uuid) {
        return null;
    };

    public void setPlayerTime(UUID uuid) {
        return;
    }
}
