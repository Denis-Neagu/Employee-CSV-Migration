package com.sparta.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeDao implements EmployeeDaoInterface{

    public EmployeeDao() {
    }

    //Fetch employee data from tables
    @Override
    public List<Employee> getAllEmployees(String tableName, Connection connection) {
        String selectQuery = "SELECT * FROM " + tableName;
        List<Employee> employees = new ArrayList<>();
        try {
            Statement preparedStatement = connection.createStatement();
            ResultSet rs = preparedStatement.executeQuery(selectQuery);
            while (rs.next()) {
                int id = rs.getInt("EMP_ID");
                String namePrefix = rs.getString("NAME_PREFIX");
                String firstName = rs.getString("FIRST_NAME");
                String middleInitial = rs.getString("MIDDLE_INITIAL");
                String lastName = rs.getString("LAST_NAME");
                String gender = rs.getString("GENDER");
                String email = rs.getString("EMAIL");
                Date dateOfBirth = rs.getDate("DATE_OF_BIRTH");
                Date dateOfJoining = rs.getDate("DATE_OF_JOINING");
                double salary = rs.getDouble("SALARY");

                Employee employee = new Employee(id, namePrefix, firstName, middleInitial.charAt(0),
                        lastName, gender.charAt(0), email, dateOfBirth, dateOfJoining, salary);

                employees.add(employee);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    //Insert each employee in tableName
    @Override
    public void insertEmployee(List<Employee> employees, String tableName, Connection connection) {
        String insertIntoTable = "INSERT INTO "+"csv_migration."+tableName+"(`EMP_ID`, `NAME_PREFIX`, `FIRST_NAME`, `MIDDLE_INITIAL`, `LAST_NAME`, `GENDER`, `EMAIL`, `DATE_OF_BIRTH`, `DATE_OF_JOINING`, `SALARY`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertIntoTable);
            connection.setAutoCommit(false);

            for (Employee employee : employees) {

                String queryCheck = "SELECT * from "+tableName+" WHERE EMP_ID = ?";

                PreparedStatement pStatementCheckIfEmployeeExists = connection.prepareStatement(queryCheck);
                pStatementCheckIfEmployeeExists.setInt(1,employee.getEmployeeID());

                ResultSet rs = pStatementCheckIfEmployeeExists.executeQuery();

                if(rs.next()) {
                    System.out.println(employee.getFirstName() + " " + employee.getLastName() + " already exists in table " + tableName);
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

    //Merge duplicates
    @Override
    public void mergeEmployees(List<Employee> employees, String tableName, Connection connection) {
        int maxID = getMaxEmployeeID(tableName, connection);
        String insertIntoTable = "INSERT INTO "+"csv_migration."+tableName+"(`EMP_ID`, `NAME_PREFIX`, `FIRST_NAME`, `MIDDLE_INITIAL`, `LAST_NAME`, `GENDER`, `EMAIL`, `DATE_OF_BIRTH`, `DATE_OF_JOINING`, `SALARY`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertIntoTable);
            connection.setAutoCommit(false);

            for (Employee employee : employees) {
                    preparedStatement.setInt(1, ++maxID);
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
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Return max EmployeeID in tableName
    @Override
    public int getMaxEmployeeID(String tableName, Connection connection) {
        String sqlQuery = "SELECT MAX(EMP_ID) FROM " + tableName;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
