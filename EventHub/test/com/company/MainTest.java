package com.company;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by rdw1995 on 10/19/16.
 */

public class MainTest {
    Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Main.createTables(conn);
        return conn;
    }

    @Test
    public void testUser () throws SQLException {
        Connection conn = startConnection();
        Event event = new Event(12,"BONAROO","dave", "Come to Bonaroo!","March 3, 2017", "www.image.com", "www.bonaroo.net","Tennessee");
        Main.insertEvent(conn,event);
        ArrayList<Event> events = Main.selectEvent(conn);
        conn.close();
        assertTrue(events.size() == 1);
    }

    @Test
    public void testLogin () throws SQLException {
        Connection conn = startConnection();
        User user = new User(12, "Rebekah", "me@gmail.com", "1234");
        Main.insertUser(conn, user);
        User user1 = Main.selectUser(conn,"me@gmail.com");
        assertTrue(user1 != null);
    }

}
