package com.sparta.data;

import java.io.File;

public class FileHandler {
    public boolean isFileValid(String filename) {
        File file = new File(filename);
        if (file.isFile() && file.exists() && file.canRead()) {
            return true;
        } else {
            return false;
        }
    }
}
