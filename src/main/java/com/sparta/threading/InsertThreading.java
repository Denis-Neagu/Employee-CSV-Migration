package com.sparta.threading;

import com.sparta.data.Employee;
import com.sparta.database.Database;

import java.sql.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InsertThreading {
    private static final Integer BATCH_SIZE = 1000;
    private final ExecutorService executorService;
    private final Database database;
    String tableName = "large_csv_employee_table";

    public InsertThreading() {
        this.executorService = Executors.newFixedThreadPool(10);
        this.database = new Database();
    }

    private void pushToMySql(List<Employee> employees, String tableName) {
        Connection connection = database.getConnection();
        String insertIntoTable = "INSERT INTO "+"csv_migration."+tableName+" (EMP_ID, NAME_PREFIX, FIRST_NAME, MIDDLE_INITIAL, LAST_NAME, GENDER, EMAIL, DATE_OF_BIRTH, DATE_OF_JOINING, SALARY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertIntoTable);
            connection.setAutoCommit(false);

            for (Employee employee : employees) {

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

                    preparedStatement.addBatch();

                    System.out.println(employee.getFirstName() + " " + employee.getLastName() + " successfully stored in " + tableName);

            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
