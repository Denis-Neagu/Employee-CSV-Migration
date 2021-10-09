package com.sparta.data;

import java.io.File;

public class FileHandler {
    final private String fileName = "EmployeeRecords.csv";

    final private String bigCSVFileName= "EmployeeRecordsLarge.csv";

    public boolean isFileValid() {
        File file = new File(fileName);
        if (file.isFile() && file.exists() && file.canRead()) {
            return true;
        } else {
            return false;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getBigCSVFileName() {
        return bigCSVFileName;
    }
}
