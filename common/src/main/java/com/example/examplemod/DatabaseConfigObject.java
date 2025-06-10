package com.example.examplemod;

public class DatabaseConfigObject {
    public DatabaseType dbType;
    public String dbHost;
    public String dbName;
    public String user;
    public String password;

    public DatabaseConfigObject(DatabaseType dbType, String dbHost, String dbName, String user, String password) {
        this.dbType = dbType;
        this.dbHost = dbHost;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
    }

    public DatabaseConfigObject() {}
}
