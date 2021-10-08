package com.sparta.launcher;

import com.sparta.data.Employee;
import com.sparta.data.EmployeeDao;
import com.sparta.data.FileHandler;
import com.sparta.data.HandleData;
import com.sparta.database.Database;
import com.sparta.database.DatabaseController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVMigration {

    public static void main(String[] args) throws SQLException {
        HandleData handleData = new HandleData();
        FileHandler fileHandler = new FileHandler();
        EmployeeDao employeeDao = new EmployeeDao();

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
                String mergeCleanTable = "merged_clean_table";

                //Establish Connection
                Connection connection = Database.getConnection();

                //Create Table
                DatabaseController databaseController = new DatabaseController();
                databaseController.createTable(employeeDuplicateTable, connection);
                databaseController.createTable(employeeCleanDataTable, connection);
                databaseController.createTable(mergeCleanTable, connection);

                //Insert Into Tables
                employeeDao.insertEmployee(employeeDuplicates, employeeDuplicateTable, connection);
                employeeDao.insertEmployee(employeeCleanCollection, employeeCleanDataTable, connection);

                //Fetch Data From Tables
                List<Employee> uniqueEmployees = employeeDao.getAllEmployees(employeeCleanDataTable, connection);
                List<Employee> duplicatedEmployees = employeeDao.getAllEmployees(employeeDuplicateTable, connection);

                //Merge
                employeeDao.insertEmployee(uniqueEmployees, mergeCleanTable, connection);

                employeeDao.mergeEmployees(duplicatedEmployees, mergeCleanTable, connection);

                connection.close();

            }
        }
    }
}
