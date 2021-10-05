package com.sparta.data;

import java.util.HashSet;

public interface Data {
    String[] getData(String fileName);
    HashSet<String> cleanData(String[] arrayName);
    void displayCleanData(HashSet<String> hashSetName);
}
