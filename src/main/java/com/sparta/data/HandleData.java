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
            while ((line = in.readLine()) != null) { //returns new line terminated by \n
                dataArray = line.split(","); //stores split string from line and returns string array
                stringArray.add(dataArray);
            }
            return stringArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Convert list of string arrays to hashset of string arrays to remove duplicates
    @Override
    public HashSet<String[]> cleanData(List<String[]> data){
        HashSet<String[]> hashSetData = new HashSet<>();
        for(String[] s : data) {
            hashSetData.add(s);
        }
        return hashSetData;
    }

    @Override
    public void displayCleanData(HashSet<String[]> hashSetName) {
        for(String[] s : hashSetName) {
            for(String value : s){
                System.out.print(value + ",");
            }
            System.out.println();
        }

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
