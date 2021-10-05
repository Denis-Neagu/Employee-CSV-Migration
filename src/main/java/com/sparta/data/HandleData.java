package com.sparta.data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HandleData implements Data{
    FileHandler fileHandler = new FileHandler();

    //Read data from file and return it as a list of string arrays
    @Override
    public List<String[]> getData() {
        String fileName = fileHandler.getFileName();
        String line = null;
        String[] dataArray = null;
        List<String[]> stringArray = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            while ((line = in.readLine()) != null) {
                dataArray = line.split(",");
                stringArray.add(dataArray);
            }
            return stringArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Convert list of string arrays to hashset to remove duplicates
    @Override
    public HashSet<String> cleanData(List<String[]> data){
        return null;
    }

    @Override
    public void displayCleanData(HashSet<String> hashSetName) {

    }

    @Override
    public void printUncleanData(List<String[]> data) {
        for (String[] s : data) {
            for (String value : s) {
                System.out.print(value + ",");
            }
            System.out.println();
        }
    }
}
