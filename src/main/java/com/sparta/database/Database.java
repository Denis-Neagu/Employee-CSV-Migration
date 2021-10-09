package com.sparta.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    String dbURL = "jdbc:mysql://localhost:3306/csv_migration?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String dbUsername = "root";
    String dbPassword = "rootpassword";

    public Database() {
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
