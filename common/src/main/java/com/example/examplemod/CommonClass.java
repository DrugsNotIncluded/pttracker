package com.example.examplemod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.example.examplemod.Constants.MOD_ID;

public class CommonClass {

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final Map<UUID, LocalDateTime> playerJoinTimes = new HashMap<>();
    public static DatabaseManager dbManager;
    public static PlayerData playerData;
    private static DatabaseConfigObject config;
    private static final DatabaseConfigObject defaultConfig = new DatabaseConfigObject(
            DatabaseType.POSTGRESQL,
            "localhost",
            "testdb",
            "test",
            "");

    public static void commonOnLogin(UUID uuid) {
        LocalDateTime join_t = LocalDateTime.now();
        playerJoinTimes.put(uuid, join_t);
    }

    public static void commonOnLogout(UUID uuid) {
        LocalDateTime exit_t = LocalDateTime.now();
        LocalDateTime join_t = playerJoinTimes.get(uuid);
        Duration sessionDuration = Duration.between(join_t, exit_t);
        try {
            playerData.addPlayer(uuid, sessionDuration.toMillis());
        } catch (SQLException e) {
            LOGGER.info("Player with UUID {} already exists, skipping.", uuid);
        }
        long allPlayTime = 0;
        try {
            allPlayTime = playerData.getTimeByUUID(uuid);
        } catch (SQLException e) {
            LOGGER.info(e.toString());
        }
        try {
            playerData.setPlayerTime(uuid, (int) (allPlayTime + sessionDuration.toMillis()));
        } catch (SQLException e) {
            LOGGER.info(e.toString());
        }
        LOGGER.info("UUID: " + uuid + " Leave " + sessionDuration.toMillis());
    }

    public static void init() {

        Path configPath = FileSystems.getDefault().getPath("config", MOD_ID, "db.json");
        config = DatabaseConfigManager.load(configPath, defaultConfig);
        dbManager = new DatabaseManager(config.dbType.toString().toLowerCase(),config.dbHost, config.dbName, config.user, config.password);
        playerData = new PlayerData(dbManager.getConnection());
    }
}