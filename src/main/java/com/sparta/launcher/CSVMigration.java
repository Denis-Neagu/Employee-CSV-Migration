package com.sparta.launcher;

import com.sparta.data.Employee;
import com.sparta.data.HandleData;


import java.util.ArrayList;

public class CSVMigration {

    public static void main(String[] args) {
        HandleData handleData = new HandleData();

        ArrayList<Employee> listOfEmployees = handleData.getUncleanDataAsEmployee();


    }
}
