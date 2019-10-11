package com.company;

import org.h2.tools.Server;
import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws SQLException {
        Server.createWebServer().start();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS seizures (id IDENTITY, type VARCHAR, trigger VARCHAR, tOfDay VARCHAR, times INT, length VARCHAR, location VARCHAR, user_id INT)");
        stmt.execute("CREATE TABLE IF NOT EXISTS users (id IDENTITY, name VARCHAR, password VARCHAR)");

        Spark.get(
                "/",
                (request, response) -> {
                    Session session = request.session();
                    String username = session.attribute("username");
                    HashMap m = new HashMap();
                    if (username == null){
                        return new ModelAndView(m, "login.html");
                    }
                    User user = selectUser(conn, username);
//                    selectSeizure(conn, user.id);
                    m.put("seizures", selectSeizure(conn, user.id));
                    m.put("username", username);

                    return new ModelAndView(m,"home.html");
                },
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/login",
                (request, response) -> {
                    String name = request.queryParams("username");
                    String password = request.queryParams("password");
                    if (name == null || password == null){
                        throw new Exception("I didn't catch that - please try again.");
                    }

                    User user = selectUser(conn, name);
                    if (user == null){
                        submitUser(conn, name, password );
                    }
                    else if (!password.equals(user.password)){
                        Spark.halt(400);
                        System.out.println("stop it");
                    }

                    Session session = request.session();
                    session.attribute("username", name);

                    response.redirect("/");
                    return "";
                }

        );

        Spark.post(
                "/submit-seizure",
                (request, response) -> {
                    Session session = request.session();
                    String username = session.attribute("username");
                    if (username == null){
                        Spark.halt(403);
                        System.out.println("You're not logged in!");
                    }

                    User user = selectUser(conn, username);

                    String type = request.queryParams("type");
                    String trigger = request.queryParams("trigger");
                    String tOfDay = request.queryParams("tOfDay");
                    int times = Integer.valueOf(request.queryParams("times"));
                    String length = request.queryParams("length");
                    String location = request.queryParams("location");

//                    User user = selectUser(conn, "username");
//                    if (user == null){
//                        Spark.halt(403);
//                        System.out.println("Please login!");
//                    }
                    submitSeizure(conn, type, trigger, tOfDay, times, length, location, user.id );
                    response.redirect("/");
                    return "";
                }
        );

        Spark.post(
                "/delete-seizure",
                (request, response) -> {
                    Session session = request.session();
                    String username = session.attribute("username");

                    if (username == null){
                        throw new Exception("You aren't logged in!");
                    }

                    int id = Integer.valueOf(request.queryParams("id"));

                    deleteSeizure(conn, id);

                    response.redirect("/");
                    return "";
                }
        );

        Spark.post(
                "/edit-seizure",
                (request, response) -> {
                    Session session = request.session();
                    String username = session.attribute("username");
                    if (username == null){
                        throw new Exception("You need to login.");
                    }

                    String type = request.queryParams("type");
                    String trigger = request.queryParams("trigger");
                    String tOfDay = request.queryParams("tOfDay");
                    int times = Integer.valueOf(request.queryParams("times"));
                    String length = request.queryParams("length");
                    String location = request.queryParams("location");
                    int id = Integer.valueOf(request.queryParams("id"));

                    seizureEdit(conn,type, trigger, tOfDay, times, length, location, id);

                    response.redirect("/");
                    return "";

                }
        );

        Spark.post(
                "/logout",
                (request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                }
        );
    }

    public static void submitSeizure (Connection conn, String type, String trigger, String tOfDay, int times, String length, String location, int userId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO seizures VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, type);
        stmt.setString(2, trigger);
        stmt.setString(3, tOfDay);
        stmt.setInt(4, times);
        stmt.setString(5, length);
        stmt.setString(6, location);
        stmt.setInt(7, userId);
        stmt.execute();
    }

    public static ArrayList<Seizure> selectSeizure (Connection conn, int userId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM seizures INNER JOIN users ON seizures.user_id = users.id WHERE users.id= ?");
        stmt.setInt(1, userId);
        ArrayList<Seizure> seizures = new ArrayList<>();
        ResultSet results = stmt.executeQuery();
        while (results.next()){
            int id = results.getInt("id");
            String type = results.getString("type");
            String trigger = results.getString("trigger");
            String tOfDay = results.getString("tOfDay");
            int times = results.getInt("times");
            String length = results.getString("length");
            String location = results.getString("location");
            seizures.add(new Seizure(id,type, trigger, tOfDay, times, length, location));
        }
        return seizures;
    }

    public static void deleteSeizure (Connection conn, int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM seizures WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    public static Seizure seizureEdit (Connection conn, String type, String trigger, String tOfDay, int times, String length, String location, int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE seizures SET type = ?, trigger = ?, tOfDay = ?, times = ?, length =?, location = ?, id = ?");
        stmt.setString(1, type);
        stmt.setString(2, trigger);
        stmt.setString(3, tOfDay);
        stmt.setInt(4, times);
        stmt.setString(5, length);
        stmt.setString(6, location);
        stmt.setInt(7, id);
        stmt.execute();
        return new Seizure(id, type, trigger, tOfDay, times,length, location);
    }

    public static void submitUser (Connection conn, String name, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES(NULL, ?, ?)");
        stmt.setString(1, name);
        stmt.setString(2, password);
        stmt.execute();

    }

    public static User selectUser (Connection conn, String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
        stmt.setString(1, name);
        ResultSet results = stmt.executeQuery();
        if (results.next()){
            int id = results.getInt("id");
            String password = results.getString("password");
            return new User(id, name, password);
        }
        return null;
    }
}

