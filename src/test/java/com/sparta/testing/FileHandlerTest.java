package com.sparta.testing;

import com.sparta.data.FileHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    FileHandler fileHandler;

    @BeforeEach
    public void setUp() {
        fileHandler = new FileHandler();
    }

    @Test
    public void isFileValid_ReturnsNotValid() {
        String s = "DefinitelyAFile";
        assertFalse(fileHandler.isFileValid(s));
    }

    @Test
    public void isFileValid_ReturnsValid() {
        assertTrue(fileHandler.isFileValid("EmployeeRecordsLarge.csv"));
    }

    @Test
    public void getFileName_ReturnsCorrectFileName() {
        String s1 = "EmployeeRecords.csv";
        assertEquals(s1, fileHandler.getFileName());
    }

    @Test
    public void getFileName_ReturnsCorrectLargeFileName() {
        String s1 = "EmployeeRecordsLarge.csv";
        assertEquals(s1, fileHandler.getBigCSVFileName());
    }
}
