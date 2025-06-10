package com.example.examplemod;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class DatabaseConfigAdapter implements JsonSerializer<DatabaseConfigObject> {
    @Override
    public JsonElement serialize(DatabaseConfigObject src, Type typeOfSrc,
                                 JsonSerializationContext context) {

        JsonObject obj = new JsonObject();

        obj.addProperty("user", src.user);
        obj.addProperty("password", src.password);
        obj.addProperty("dbHost", src.dbHost);
        obj.addProperty("dbName", src.dbName);
        obj.addProperty("dbType", src.dbType.toString());

        return obj;
    }
}