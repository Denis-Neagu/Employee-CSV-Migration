package com.sparta.launcher;

import com.sparta.data.HandleData;

import java.util.HashSet;
import java.util.List;

public class CSVMigration {

    public static void main(String[] args) {
        HandleData handleData = new HandleData();
        List<String[]> result = handleData.getData();

        HashSet<String[]> hashSetCleanData = handleData.cleanData(result);
        handleData.displayCleanData(hashSetCleanData);
    }
}
