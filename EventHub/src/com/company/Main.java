package com.company;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import org.h2.tools.Server;
import spark.Session;
import spark.Spark;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    static void createTables (Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS events (id IDENTITY, name VARCHAR, author VARCHAR, text VARCHAR, " +
                "date VARCHAR, image VARCHAR, website VARCHAR, location VARCHAR)");
        stmt.execute("CREATE TABLE IF NOT EXISTS users(id IDENTITY, name VARCHAR, email VARCHAR, password VARCHAR)");
    }

    public static void insertUser(Connection conn, User user) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (NULL, ?,?,?)");
        stmt.setString(1, user.name);
        stmt.setString(2, user.email);
        stmt.setString(3, user.password);
        stmt.execute();
    }

    public static User selectUser(Connection conn, String email) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
        stmt.setString(1, email);
        ResultSet results = stmt.executeQuery();
        if (results.next()) {
            int id = results.getInt("id");
            String name = results.getString("name");
            String password = results.getString("password");
            return new User(id, name, email, password);
        }
        return null;
    }

    public static User editUser (Connection conn, User user) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?");
        stmt.setString(1, user.name);
        stmt.setString(2, user.email);
        stmt.setString(3, user.password);
        stmt.setInt(4, user.id);
        stmt.execute();
        return new User(user.id, user.name,user.email, user.password);
    }


    static void insertEvent (Connection conn, Event event) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO events VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, event.name);
        stmt.setString(2, event.author);
        stmt.setString(3, event.text);
        stmt.setString(4, event.date);
        stmt.setString(5, event.image);
        stmt.setString(6, event.website);
        stmt.setString(7, event.location);
        stmt.execute();
    }

    static ArrayList<Event> selectEvent (Connection conn) throws SQLException {
        ArrayList<Event> events = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM events");
        ResultSet results = stmt.executeQuery();
        while (results.next()){
            int id = results.getInt("events.id");
            String name = results.getString("events.name");
            String author = results.getString("events.author");
            String text = results.getString("events.text");
            String date = results.getString("events.date");
            String image = results.getString("events.image");
            String website = results.getString("events.website");
            String location = results.getString("events.location");
            events.add(new Event(id,name,author, text, date, image, website, location));
        }
        return events;
    }

    static void deleteEvent (Connection conn, int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM events WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    static Event editEvent (Connection conn, Event event, User user) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE events SET name = ?, author = ?, text = ?, date = ?, " +
                "image = ?, " + "website = ?, location = ?");
        stmt.setString(1, event.name);
        stmt.setString(2, event.author);
        stmt.setString(3, event.text);
        stmt.setString(4, event.date);
        stmt.setString(5, event.image);
        stmt.setString(6, event.website);
        stmt.setString(7, event.location);
        stmt.execute();
        return new Event(event.id, event.name, event.author, event.text, event.date,
                event.image, event.website, event.location);
    }



    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        createTables(conn);
        Server.createWebServer().start();
        Spark.externalStaticFileLocation("public");
        Spark.init();


        Spark.post(
                "/login",
                (request, response) -> {
                    String body = request.body();
                    JsonParser parser = new JsonParser();
                    User user = parser.parse(body, User.class);
                    User userFromDb = selectUser(conn, user.email);
                    if (userFromDb == null) {
                        insertUser(conn, user);
                    }
                    else if (!user.password.equals(userFromDb.password)) {
                        Spark.halt(400);
                        System.out.println("That password is incorrect!");
                        return "";
                    }
                    Session session = request.session();
                    session.attribute("email", user.email);
                    return "";
                }
        );

        Spark.get(
                "/user",
                (request, response) -> {
                    Session session = request.session();
                    String email = session.attribute("email");
                    if (email != null) {
                        User user = selectUser(conn, email);
                        JsonSerializer serializer = new JsonSerializer();
                        return serializer.serialize(user);
                    }
                    return "";
                }
        );

        Spark.post(
                "/edit-user",
                (request, response) -> {
                    String body = request.body();
                    JsonParser p = new JsonParser();
                    User user = p.parse(body, User.class);
                    JsonSerializer s = new JsonSerializer();
                    return s.serialize(editUser(conn, user));
                }
        );

        Spark.post(
                "/add-event",
                (request, response) -> {
                    JsonParser p = new JsonParser();
                    String body = request.body();
                    Event event = p.parse(body, Event.class);
                    if (event.location == null){
                        Spark.halt(400);
                        System.out.println("Please enter valid fields.");
                    }
                    insertEvent(conn, event);
                    return "";
                }
        );

        Spark.post(
                "/delete-event/:id",
                (request, response) -> {
                   JsonParser parser = new JsonParser();
                   int id = parser.parse(request.params(":id"));
                   deleteEvent(conn, id);
                   return "Event Deleted.";
                }
        );

        Spark.get(
                "/events",
                (request, response) -> {
                    JsonSerializer s = new JsonSerializer();
                    return  s.serialize(selectEvent(conn));
                }
        );

        Spark.post(
                "/edit-event",
                (request, response) -> {
                    Session session = request.session();
                    String email = session.attribute("email");
                    if (email == null) {
                        return "";
                    }
                    User user = selectUser(conn, email);
                    JsonParser p = new JsonParser();
                    String body = request.body();
                    Event event = p.parse(body, Event.class);
                    Event editEvent = editEvent(conn, event, user);
                    JsonSerializer s = new JsonSerializer();
                    return s.serialize(editEvent);
                }
        );

        Spark.get(
                "/logout",
                (request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    return "";
                }
        );
    }
}
