import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rdw1995 on 10/3/16.
 */
public class Main {
    static HashMap<String, User> users = new HashMap<>();
    static ArrayList<User> pastUser = new ArrayList<>();

    public static void main(String[] args) {
        Spark.get(
                "/",
                (request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("loginName");
                    User user  = users.get(name);
                    HashMap m = new HashMap();
                    if (user != null){
                        m.put("name", pastUser);
                    }
                    m.put("users", pastUser);
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
                    String name = request.queryParams("username");
                    User user = users.get(name);
                    if(user == null){
                        user = new User(name);
                        users.put(name, user);
                    }

                    Session session = request.session();
                    session.attribute("loginName", name); // similar to 'put' into session

                    pastUser.add(user);
                    response.redirect("/");
                    return null;
                }
        );

        Spark.post(
                "/logout",
                (request, response) -> {
                    Session session = request.session();
                    session.invalidate(); // kills only your session - logs YOU out not everyone
                    response.redirect("/");
                    return null;
                }
        );
    }
}
