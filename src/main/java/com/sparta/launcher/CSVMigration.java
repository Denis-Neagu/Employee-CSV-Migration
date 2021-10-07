package com.sparta.launcher;

import com.sparta.data.Employee;
import com.sparta.data.HandleData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVMigration {

    public static void main(String[] args) {
        HandleData handleData = new HandleData();

        ArrayList<Employee> listOfEmployees = handleData.getUncleanDataAsEmployee();

        Map<Integer, List<Employee>> mapOfEmployees = handleData.mapEmployees(listOfEmployees);

        List<Employee> employeeDuplicates = handleData.getEmployeeData(mapOfEmployees, 2);

        List<Employee> employeeCleanCollection = handleData.getEmployeeData(mapOfEmployees, 1);

        System.out.println("There are " + employeeDuplicates.size() + " duplicated rows to be removed because their employeeID is not unique");
        System.out.println("There are " + employeeCleanCollection.size() + " unique rows after removing duplicates.");
    }
}
