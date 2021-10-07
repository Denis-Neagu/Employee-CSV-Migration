package com.sparta.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {
    private String dbURL = "jdbc:mysql://localhost:3306/csv_migration";
    private String dbUsername = "root";
    private String dbPassword = "rootpassword";

    public Connection getConn() throws SQLException {
        return DriverManager.getConnection(dbURL,dbUsername,dbPassword);
    }


}
