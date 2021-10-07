package com.sparta.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() {
        String dbURL = "jdbc:mysql://localhost:3306/csv_migration";
        String dbUsername = "root";
        String dbPassword = "rootpassword";

        try {
            connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (instance == null) {
            instance = new Database();
        }

        return instance.connection;
    }


}
