package com.sparta.database;

import java.sql.*;

public class DatabaseController {
    Connection connection = Database.getConnection();

    public void createTable(String tableName) {
        String createTable = "CREATE TABLE IF NOT EXISTS "+"csv_migration."+tableName+"(\n" +
                "  `EMP_ID` INT NOT NULL,\n" +
                "  `NAME_PREFIX` VARCHAR(45) NULL,\n" +
                "  `FIRST_NAME` VARCHAR(45) NULL,\n" +
                "  `MIDDLE_INITIAL` CHAR NULL,\n" +
                "  `LAST_NAME` VARCHAR(45) NULL,\n" +
                "  `GENDER` CHAR NULL,\n" +
                "  `EMAIL` VARCHAR(100) NULL,\n" +
                "  `DATE_OF_BIRTH` DATE NULL,\n" +
                "  `DATE_OF_JOINING` DATE NULL,\n" +
                "  `SALARY` DOUBLE NULL,\n" +
                "  PRIMARY KEY (`EMP_ID`))";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTable);

            System.out.println(tableName +" TABLE CREATED");

            ResultSet rs = statement.executeQuery("SELECT * FROM "+tableName);

            if (rs.next()) {
                statement.close();
                connection.close();
            }

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }


}
