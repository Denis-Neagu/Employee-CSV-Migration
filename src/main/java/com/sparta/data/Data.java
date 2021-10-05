package com.sparta.data;

import java.util.HashSet;
import java.util.List;

public interface Data {
    List<String[]> getData();
    HashSet<String> cleanData(List<String[]> data);
    void displayCleanData(HashSet<String> hashSetName);
    void printUncleanData(List<String[]> data);

}
