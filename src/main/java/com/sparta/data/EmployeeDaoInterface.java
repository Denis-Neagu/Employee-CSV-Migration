package com.sparta.data;

import java.sql.Connection;
import java.util.List;

public interface EmployeeDaoInterface {
    List<Employee> getAllEmployees(String tableName, Connection connection);
    Employee getEmployee(int employeeID);
    void updateEmployeeID(Employee employee);
    void deleteEmployee(Employee employee);
}
