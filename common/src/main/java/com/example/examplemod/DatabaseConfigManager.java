package com.example.examplemod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Files;
import java.nio.file.Path;


public class DatabaseConfigManager {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(DatabaseConfigObject.class, new DatabaseConfigAdapter())
            .registerTypeAdapter(DatabaseConfigObject.class, new DatabaseConfigDeserializer())
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    public static DatabaseConfigObject load(Path path, DatabaseConfigObject defaultConfig) {
        try {
            if (!Files.exists(path)) {
                save(path, defaultConfig);
                return defaultConfig;
            }
            String json = Files.readString(path);
            return GSON.fromJson(json, DatabaseConfigObject.class);
        } catch (Exception e) {
            Constants.LOG.info(e.toString());
            return defaultConfig;
        }
    }

    public static void save(Path path, DatabaseConfigObject config) {
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, GSON.toJson(config));
        } catch (Exception e) {
            Constants.LOG.info(e.toString());
        }
    }
}
