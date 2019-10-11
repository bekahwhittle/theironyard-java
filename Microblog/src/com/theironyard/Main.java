package com.theironyard;

import com.sun.tools.internal.ws.processor.model.Model;
import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
       Spark.get(
               "/",
               (request, response) -> {
                   Session session = request.session();
                   String name = session.attribute("loginName");
                   User user = users.get(name);
                   HashMap m = new HashMap();
                   if(user == null){
                       return new ModelAndView(m, "home.html");
                   }
                   m.put("name",user.name);
                   m.put("messages", user.messages);
                   return new ModelAndView(m, "home.html");
               },
               new MustacheTemplateEngine()
       );

       Spark.get(
               "/login",
               (request, response) -> {
                   return new ModelAndView(null, "login.html");
               },
               new MustacheTemplateEngine()
       );

       Spark.post(
               "/login",
               (request, response) -> {
                   String name = request.queryParams("loginName");
                   String password = request.queryParams("password");
                   User user = users.get(name);
                   if (user == null){
                       user = new User(name,password);
                       users.put(name,user);
                   }
                   else if (!password.equals(user.password)){
                       response.redirect("/");
                       return null;
                   }
                   Session session = request.session();
                   session.attribute("loginName",name);
                   response.redirect("/");
                   return null;
               }
       );

       Spark.post(
               "/logout",
               (request, response) -> {
                   Session session = request.session();
                   session.invalidate();
                   response.redirect("/");
                   return null;
               }
       );

       Spark.post(
               "/create-message",
               (request, response) -> {
                   Session session = request.session();
                   String name = session.attribute("loginName");
                   User user = users.get(name);
                   Message message = new Message(request.queryParams("message"));
                   ArrayList<Message> m1 = user.messages;
                   m1.add(message);
                   response.redirect("/");
                   return null;
               }
       );

       Spark.post(
               "/delete-message",
               (request, response) -> {
                   Session session = request.session();
                   String name = session.attribute("loginName");
                   User user = users.get(name);
                   int i = Integer.valueOf(request.queryParams("message"));
                   user.messages.remove(i - 1);
                   response.redirect("/");
                   return null;
               }
       );
    }
}
