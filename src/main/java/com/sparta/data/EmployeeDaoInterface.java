package com.sparta.data;

import java.sql.Connection;
import java.util.List;

public interface EmployeeDaoInterface {
    List<Employee> getAllEmployees(String tableName, Connection connection);
    void insertEmployee(List<Employee> employees, String tableName, Connection connection);
    void mergeEmployees(List<Employee> employees, String tableName, Connection connection);
    int getMaxEmployeeID(String tableName, Connection connection);
    void truncateData(String tableName, Connection connection);
    void updateEmployeeEmail(String tableName, Connection connection, String newEmail, int empId);
}
