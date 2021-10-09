package com.sparta.data;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class HandleData implements HandleDataInterface {
    FileHandler fileHandler = new FileHandler();

    //Read data from file and return it as a collection of Employee
    @Override
    public ArrayList<Employee> getDataFromCSV(String fileName) {
        String line = null;
        String[] dataArray = null;
        ArrayList<Employee> employeeList = new ArrayList<>();
        Employee employee;

        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            in.readLine(); //skip columns

            while ((line = in.readLine()) != null) { //returns new line terminated by \n
                dataArray = line.split(","); //stores split string from line and returns string array

                Date date1 = new Date(dataArray[7]);
                Date date2 = new Date(dataArray[8]);

                java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
                java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());

                employee = new Employee(Integer.parseInt(dataArray[0]), dataArray[1], dataArray[2], dataArray[3].charAt(0), dataArray[4], dataArray[5].charAt(0), dataArray[6], sqlDate1, sqlDate2, Double.parseDouble(dataArray[9]));

                employeeList.add(employee);
            }

            return employeeList;

        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Map employee to employeeID
    @Override
    public Map<Integer,List<Employee>> mapEmployees(List<Employee> listOfEmployees) {
        Map<Integer, List<Employee>> mapOfEmployees = new HashMap<>();

        for (Employee employee : listOfEmployees) {
            List<Employee> list = mapOfEmployees.get(employee.getEmployeeID());

            if(list == null) {
                list = new ArrayList<>();
            }

            list.add(employee);

            mapOfEmployees.put(employee.getEmployeeID(), list);

        }

        return mapOfEmployees;
    }

    //Get/remove duplicated data and unique employees
    @Override
    public List<Employee> getEmployeeData(Map<Integer,List<Employee>> mapOfEmployees, int occurrence) {
        List<Employee> listOfEmployees = new ArrayList<>();

        for (Map.Entry<Integer,List<Employee>> entry : mapOfEmployees.entrySet()) {
            if (entry.getValue().size() == occurrence) {
                Employee tempEmployee = entry.getValue().get(0);

                listOfEmployees.add(tempEmployee);

                entry.getValue().remove(tempEmployee);
            }
        }

        return listOfEmployees;
    }

    //Create regex which returns true or false if data is fit for storage
    @Override
    public boolean isValid(List<Employee> employees) {
        boolean valid = true;

        if (employees.isEmpty()) {
            valid = false;
        }

        for (Employee employee : employees) {
            if (employee.getEmployeeID() < 0) {
                valid = false;
            } else if (!employee.getNamePrefix().matches("[a-zA-Z]+\\.")) {
                valid = false;
            } else if (!employee.getFirstName().matches("[a-zA-Z]+")) {
                valid = false;
            } else if ((String.valueOf(employee.getMiddleInitial()).length() > 1) || !String.valueOf(employee.getMiddleInitial()).matches("[A-Z]")) {
                valid = false;
            } else if (!employee.getLastName().matches("[a-zA-Z]+")) {
                valid = false;
            } else if ((String.valueOf(employee.getGender()).length() > 1) || !String.valueOf(employee.getGender()).matches("^M$|^F$|^M/F$")) {
                valid = false;
            } else if (!employee.getEmail().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                valid = false;
            } else if (!(String.valueOf(employee.getDateOfBirth())).matches("^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")) {
                valid = false;
            } else if (!(String.valueOf(employee.getDateOfJoining())).matches("^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")) {
                valid = false;
            } else if (employee.getSalary() < 0) {
                valid = false;
            }
        }
        return valid;
    }
}
