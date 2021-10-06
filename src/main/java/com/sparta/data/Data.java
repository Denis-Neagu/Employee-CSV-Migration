package com.sparta.data;

import java.util.List;
import java.util.Map;

public interface Data {
    List<Employee> getUncleanDataAsEmployee();
    Map<Integer, List<Employee>> mapEmployees(List<Employee> listOfEmployees);
    List<Employee> getEmployeeData(Map<Integer,List<Employee>> mapOfEmployees, int occurrence);
}
