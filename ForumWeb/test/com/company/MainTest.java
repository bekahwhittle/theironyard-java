package com.company;

import com.company.Main;
import com.company.Message;
import com.company.User;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.junit.Assert.assertTrue;

/**
 * Created by rdw1995 on 10/13/16.
 */
public class MainTest {
    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Main.createTables(conn);
        return conn;
    } // utility method our tests will use

    @Test
    public void testUser() throws SQLException {
        Connection conn = startConnection();
        Main.insertUser(conn, "Alice", "pass");
        User user = Main.selectUser(conn, "Alice");
        conn.close();
        assertTrue(user != null);
    }

    @Test
    public void testMessage() throws SQLException {
        Connection conn = startConnection();
        Main.insertUser(conn, "Alice", "pass");
        User user = Main.selectUser(conn, "Alice");
        Main.insertMessage(conn, -1, "Hello Y'all!", user.id);
        Message message = Main.selectMessage(conn, 1);
        conn.close();
        assertTrue(message != null);
    }
}
