package com.sparta.testing;

import com.sparta.data.Employee;
import com.sparta.data.HandleData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandleDataTest {
    HandleData handleData;

    @BeforeEach
    public void setUp() {
        handleData = new HandleData();
    }

    @Test
    public void getDataFromCSV_ChecksDifferentEmployeeIDS() {
        List<Employee> employeeList = handleData.getDataFromCSV("EmployeeRecordsLarge.csv");
        Employee e1 = employeeList.get(0);
        Employee e2 = employeeList.get(1);

        assertNotEquals(e1, e2);
    }

    @Test
    public void getDataFromCSV_DoesItReturnsDataAsList() {
        ArrayList<Employee> employeeArrayList1 = handleData.getDataFromCSV("EmployeeRecordsLarge.csv");
        ArrayList<Employee> employeeArrayList2 = handleData.getDataFromCSV("EmployeeRecords.csv");

        assertNotEquals(employeeArrayList1, employeeArrayList2);
    }

    @Test
    public void getDataFromCSV_CheckIfItReturnsCorrectData() {
        ArrayList<Employee> employeeArrayList1 = handleData.getDataFromCSV("EmployeeRecords.csv");
        ArrayList<Employee> employeeArrayList2 = handleData.getDataFromCSV("EmployeeRecords.csv");

        assertEquals(employeeArrayList1, employeeArrayList2);
    }

    @Test
    public void isValid_CheckIfProperDataReturnsTrue() {
        ArrayList<Employee> employeeArrayList1 = handleData.getDataFromCSV("EmployeeRecordsLarge.csv");
        boolean tempBooleanValue = handleData.isValid(employeeArrayList1);

        assertTrue(tempBooleanValue);
    }

    @Test
    public void isValid_CheckIfCorruptDataInEmployeeID() {
        ArrayList<Employee> employeeArrayList = handleData.getDataFromCSV("EmployeeRecords.csv");
        Employee employee0 = employeeArrayList.get(0);
        employee0.setEmployeeID(-30);

        assertFalse(handleData.isValid(employeeArrayList));
    }

    @Test
    public void isValid_CheckIfCorruptDataInEmail() {
        ArrayList<Employee> employeeArrayList = handleData.getDataFromCSV("EmployeeRecords.csv");
        Employee employee0 = employeeArrayList.get(0);
        employee0.setEmail("thisisthebestemailevertobehonest");

        assertFalse(handleData.isValid(employeeArrayList));
    }

    @Test
    public void isValid_CheckIfCorruptedDataInFirstName() {
        ArrayList<Employee> employeeArrayList = handleData.getDataFromCSV("EmployeeRecords.csv");
        Employee employee0 = employeeArrayList.get(0);
        employee0.setFirstName("denis...");

        assertFalse(handleData.isValid(employeeArrayList));
    }

    @Test
    public void isValid_CheckIfCorruptDataInLastName() {
        ArrayList<Employee> employeeArrayList = handleData.getDataFromCSV("EmployeeRecords.csv");
        Employee employee0 = employeeArrayList.get(0);
        employee0.setLastName("neagu....---");

        assertFalse(handleData.isValid(employeeArrayList));
    }

    @Test
    public void isValid_CheckIfCorruptDataInDateOfBirth() {
        ArrayList<Employee> employeeArrayList = handleData.getDataFromCSV("EmployeeRecords.csv");
        Employee employee0 = employeeArrayList.get(0);

        String tempDateString = "1999-05-28";
        Date date = Date.valueOf(tempDateString);

        employee0.setDateOfBirth(date);

        assertTrue(handleData.isValid(employeeArrayList));
    }

    @Test
    public void isValid_CheckIfCorruptDataInGender() {
        ArrayList<Employee> employeeArrayList = handleData.getDataFromCSV("EmployeeRecords.csv");
        Employee employee0 = employeeArrayList.get(0);
        employee0.setGender('W');

        assertFalse(handleData.isValid(employeeArrayList));
    }

    @Test
    public void getEmployeeData_ReturnsEmptyArrayWhenOccurrenceIsNotValid() {
        ArrayList<Employee> employeeArrayList0 = handleData.getDataFromCSV("EmployeeRecords.csv");
        Map<Integer, List<Employee>> mapOfEmployees = handleData.mapEmployees(employeeArrayList0);
        List<Employee> employeeArrayList = handleData.getEmployeeData(mapOfEmployees, 5);

        List<Employee> list = new ArrayList<>(); //Empty

        assertEquals(list, employeeArrayList);
    }


}
