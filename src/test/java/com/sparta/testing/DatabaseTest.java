package com.sparta.testing;

import com.sparta.database.Database;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    @Test
    public void getConnection_returnsDifferentConnections() {
        Database database = new Database();

        Connection conn = database.getConnection();

        Connection connection = database.getConnection();

        assertNotEquals(conn,connection);
    }

}
