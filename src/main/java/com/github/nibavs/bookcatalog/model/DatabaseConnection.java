package com.github.nibavs.bookcatalog.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String URL = "jdbc:postgresql://localhost:5432/book-catalog";
    private static String USER = "postgres";
    private static String PASSWORD = "23099";

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
