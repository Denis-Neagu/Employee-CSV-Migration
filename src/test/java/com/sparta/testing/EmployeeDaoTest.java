package com.sparta.testing;

import com.sparta.data.Employee;
import com.sparta.data.EmployeeDao;
import com.sparta.data.FileHandler;
import com.sparta.data.HandleData;
import com.sparta.database.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoTest {
    EmployeeDao employeeDao;
    Database database = new Database();
    Connection connection;
    FileHandler fileHandler = new FileHandler();
    String tableName = "large_csv_employee_table";

    @BeforeEach
    public void setUp(){
        connection = database.getConnection();
        employeeDao = new EmployeeDao();
    }

    @Test
    public void getAllEmployees_ReturnsAllEmployees() {
        HandleData handleData = new HandleData();
        List<Employee> fetchedEmployees = employeeDao.getAllEmployees(tableName, connection);
        ArrayList<Employee> listOfAllEmployees = handleData.getDataFromCSV(fileHandler.getBigCSVFileName());

        assertEquals(fetchedEmployees.size(), listOfAllEmployees.size());
    }

    @Test
    public void getMaxEmployeeID_ReturnsHighestMaxEmployeeID() {

        int maxID = employeeDao.getMaxEmployeeID(tableName, connection);
        List<Employee> fetchedEmployees = employeeDao.getAllEmployees(tableName, connection);
        Employee employee = fetchedEmployees.get(fetchedEmployees.size()-1);

        assertEquals(maxID,employee.getEmployeeID());

    }



}
