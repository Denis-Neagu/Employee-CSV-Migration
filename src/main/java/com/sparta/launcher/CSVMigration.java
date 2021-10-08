package com.sparta.launcher;

import com.sparta.data.Employee;
import com.sparta.data.FileHandler;
import com.sparta.data.HandleData;
import com.sparta.database.DatabaseController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVMigration {

    public static void main(String[] args) {
        HandleData handleData = new HandleData();
        FileHandler fileHandler = new FileHandler();

        //Check if file given is valid
        if (fileHandler.isFileValid()) {
            ArrayList<Employee> listOfEmployees = handleData.getUncleanDataAsEmployee(); //Get ArrayList of all data from CSV file

            if (handleData.isValid(listOfEmployees)) { //Check if all data from CSV file matches the correct regex
                Map<Integer, List<Employee>> mapOfEmployees = handleData.mapEmployees(listOfEmployees); //Map employee per key

                List<Employee> employeeDuplicates = handleData.getEmployeeData(mapOfEmployees, 2); //Store one of the employees with a duplicated employeeID in List and remove from Map

                List<Employee> employeeCleanCollection = handleData.getEmployeeData(mapOfEmployees, 1); //Store the rest of the employees after removal in a List with only unique employeeID's

                System.out.println("There are " + employeeDuplicates.size() + " duplicated rows to be removed because their employeeID is not unique");
                System.out.println("There are " + employeeCleanCollection.size() + " unique rows after removing duplicates.\n");

                String employeeDuplicateTable = "employee_duplicates";
                String employeeCleanDataTable = "employee_clean_data";

                //Create Table
                DatabaseController databaseController = new DatabaseController();
                databaseController.createTable(employeeDuplicateTable);
                databaseController.createTable(employeeCleanDataTable);

                //Insert Into Tables
                databaseController.insertEmployee(employeeDuplicates, employeeDuplicateTable);
                databaseController.insertEmployee(employeeCleanCollection, employeeCleanDataTable);
            }
        }
    }
}
