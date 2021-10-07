package com.sparta.data;

import java.io.*;
import java.util.*;

public class HandleData implements Data{
    FileHandler fileHandler = new FileHandler();

    //Read data from file and return it as a collection of Employee
    @Override
    public ArrayList<Employee> getUncleanDataAsEmployee() {
        String fileName = fileHandler.getFileName();
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
}
