package com.github.nibavs.bookcatalog.model;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = Dotenv.load().get("DB_URL");
    private static final String USER = Dotenv.load().get("DB_USER");
    private static final String PASSWORD = Dotenv.load().get("DB_PASSWORD");

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
