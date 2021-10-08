package com.sparta.database;

import com.sparta.data.Employee;

import java.sql.*;
import java.util.List;

public class DatabaseController {
    Connection connection = Database.getConnection();

    //create table in database
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

            System.out.println(tableName +" TABLE IS IN csv_migration database");
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }
    //insert each employee in tableName
    public void insertEmployee(List<Employee> employees, String tableName) {
        String insertIntoTable = "INSERT INTO "+"csv_migration."+tableName+"(`EMP_ID`, `NAME_PREFIX`, `FIRST_NAME`, `MIDDLE_INITIAL`, `LAST_NAME`, `GENDER`, `EMAIL`, `DATE_OF_BIRTH`, `DATE_OF_JOINING`, `SALARY`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertIntoTable);
            connection.setAutoCommit(false);

            for (Employee employee : employees) {

                String queryCheck = "SELECT * from "+tableName+" WHERE EMP_ID = ?";

                PreparedStatement preparedStatement1 = connection.prepareStatement(queryCheck);
                preparedStatement1.setInt(1,employee.getEmployeeID());

                ResultSet rs = preparedStatement1.executeQuery();

                if(rs.next()) {
                    System.out.println(employee.getFirstName() + " " + employee.getLastName() + " Already exists in table " + tableName);
                } else {
                    preparedStatement.setInt(1, employee.getEmployeeID());
                    preparedStatement.setString(2, employee.getNamePrefix());
                    preparedStatement.setString(3, employee.getFirstName());
                    preparedStatement.setString(4, String.valueOf(employee.getMiddleInitial()));
                    preparedStatement.setString(5, employee.getLastName());
                    preparedStatement.setString(6, String.valueOf(employee.getGender()));
                    preparedStatement.setString(7, employee.getEmail());
                    preparedStatement.setDate(8, employee.getDateOfBirth());
                    preparedStatement.setDate(9, employee.getDateOfJoining());
                    preparedStatement.setDouble(10, employee.getSalary());

                    preparedStatement.execute();

                    System.out.println(employee.getFirstName() + " " + employee.getLastName() + " successfully stored in " + tableName);

                }
            }
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
