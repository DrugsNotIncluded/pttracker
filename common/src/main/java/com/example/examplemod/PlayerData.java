package com.example.examplemod;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.UUID;

public class PlayerData {
    private final Connection connection;

    public PlayerData(Connection connection) {
        this.connection = connection;
    }

    public void addPlayer(UUID uuid, long play_time) throws SQLException {
        String sql = "INSERT INTO playtime (uuid, play_time) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, uuid);
            stmt.setLong(2, play_time);
            stmt.executeUpdate();
        }
    }

    public long getTimeByUUID(UUID uuid) throws SQLException {
        String sql = "SELECT play_time FROM playtime WHERE uuid = ?::uuid";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, uuid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return (rs.getLong("play_time"));
            }
            return 0;
        }
    }

    public void setPlayerTime(UUID uuid, int play_time) throws SQLException {
        String sql = "UPDATE playtime SET play_time = ? WHERE uuid = ?::uuid";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, play_time);
            stmt.setString(2, uuid.toString());
            stmt.executeUpdate();
        }
    }
}
