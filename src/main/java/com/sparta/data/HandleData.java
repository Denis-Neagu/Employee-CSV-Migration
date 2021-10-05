package com.sparta.data;

import java.util.HashSet;

public class HandleData implements Data{
    //Read data from file and return it as an array
    @Override
    public String[] getData(String fileName) {
        return null;
    }

    //Convert String array to Set to remove duplicates
    @Override
    public HashSet<String> cleanData(String[] arrayName){
        return null;
    }

    @Override
    public void displayCleanData(HashSet<String> hashSetName) {}
}
