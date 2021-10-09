package com.sparta.data;

import java.util.List;
import java.util.Map;

public interface HandleDataInterface {
    List<Employee> getDataFromCSV(String fileName);
    Map<Integer, List<Employee>> mapEmployees(List<Employee> listOfEmployees);
    List<Employee> getEmployeeData(Map<Integer,List<Employee>> mapOfEmployees, int occurrence);
    boolean isValid(List<Employee> employees);
}
