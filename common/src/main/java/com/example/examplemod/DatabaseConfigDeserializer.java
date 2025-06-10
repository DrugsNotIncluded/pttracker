package com.example.examplemod;

import com.google.gson.*;

import java.lang.reflect.Type;

public class DatabaseConfigDeserializer implements JsonDeserializer<DatabaseConfigObject> {
    @Override
    public DatabaseConfigObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        DatabaseConfigObject config = new DatabaseConfigObject();

        if (jsonObject.has("dbType")) {
            String dbTypeStr = jsonObject.get("dbType").getAsString();
            try {
                config.dbType = DatabaseType.valueOf(dbTypeStr);
            } catch (IllegalArgumentException e) {
                throw new JsonParseException("Invalid database type: " + dbTypeStr);
            }
        }

        if (jsonObject.has("dbHost")) config.dbHost = jsonObject.get("dbHost").getAsString();
        if (jsonObject.has("dbName")) config.dbName = jsonObject.get("dbName").getAsString();
        if (jsonObject.has("user")) config.user = jsonObject.get("user").getAsString();
        if (jsonObject.has("password")) config.password = jsonObject.get("password").getAsString();

        return config;
    }
}
