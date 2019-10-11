package com.company;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by rdw1995 on 10/18/16.
 */
public class MainTest {
    Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Main.createTable(conn);
        return conn;
    }

    @Test

    public void testUser () throws SQLException {
        Connection conn = startConnection();
        User user = new User(12,"Alice","Street","tward4@tulane.edu");
        Main.insertUser(conn,user);
        ArrayList<User> users = Main.selectUser(conn);
        conn.close();
        assertTrue(users.size() == 1);
    }

    @Test

    public void testEditUser () throws SQLException {
        Connection conn = startConnection();
        User user = new User(12,"Alice","Street","tward4@tulane.edu");
        Main.insertUser(conn,user);
        Main.updateUser(conn, user );
        User user1 = new User(12, "Bob", "Home", "bj@gmail.com");
        ArrayList<User> users = Main.selectUser(conn);
        conn.close();
        assertTrue(users.size() == 1);
    }

    @Test

    public void testDeleteUser () throws SQLException {
        Connection conn = startConnection();
        User user1 = new User(12,"Alice","Street","tward4@tulane.edu");
        Main.insertUser(conn,user1);

        Main.deleteUser(conn, user1);


    }

}