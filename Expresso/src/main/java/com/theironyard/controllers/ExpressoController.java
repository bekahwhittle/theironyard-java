package com.theironyard.controllers;

import com.sun.org.apache.regexp.internal.RE;
import com.theironyard.entities.Shop;
import com.theironyard.entities.User;
import com.theironyard.services.ShopRepo;
import com.theironyard.services.UserRepo;
import com.theironyard.utitilies.PasswordStorage;
import org.apache.catalina.valves.rewrite.RewriteCond;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rdw1995 on 11/10/16.
 */

@RestController
public class ExpressoController {
    @Autowired
    ShopRepo shops;

    @Autowired
    UserRepo users;

    Server h2;

    @PostConstruct
    public void init() throws SQLException, PasswordStorage.CannotPerformOperationException {
        h2 = Server.createWebServer().start();

        if (shops.count() == 0) {
            User user = new User("Bekah", PasswordStorage.createHash("TrumpTrain"));
            users.save(user);
            shops.save(new Shop("Kudu", "4 Vanderhorst St, Charleston, SC 29403", "6:45am - 9pm", "http://www.kuducoffeeandcraftbeer.com/", "http://www.kuducoffeeandcraftbeer.com/sites/default/files/styles/photo_1024/public/photos/IMG_5770.jpg?itok=e5xyC6f3", "Hip coffeehouse with a courtyard patio also serves craft beers & hosts live music on the weekends.", 3, user));
            shops.save(new Shop("City Lights Coffee", "141 Market St, Charleston, SC 29401", "7am - 7:30pm", "https://www.facebook.com/citylightcoffee/", "http://www.anafterthought.com/plugins/elfinder/files/IMG_5509.jpg", "Drip, French press, espresso & tea drinks with cafe breakfast & lunch fare in artful digs with WiFi.", 5, user));
            shops.save(new Shop("Starbucks Coffee", "King Street - Charleston, SC", "5am - 10pm", "https://www.starbucks.com/", "http://www.kowalskis.com/sites/kowalskis.com/files/images/departments/starbucks-logo-trans.png", "Seattle-based coffeehouse chain known for its signature roasts, light bites and WiFi availability.", 23, user));
            shops.save(new Shop("Collective Coffee Co", "Mt. Pleasant, SC", "7am - 5pm", "http://collective-coffee.com/", "https://static.squarespace.com/static/50f1db62e4b0ceb75bca6fdd/t/51044ad8e4b00bb1fb60cc11/1359235800677/Counter%20Collective%20Fix-3220%20bg.jpg", "A modern feel with quaint taste.", 7, user));
        }
    }

    @PreDestroy
    public void destroy() {
        h2.stop();
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> addUser (HttpSession session, @RequestBody User user) throws PasswordStorage.CannotPerformOperationException, PasswordStorage.InvalidHashException {
        User userFromDb = users.findFirstByName(user.getName());
        if (userFromDb == null){
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(user.getPassword(), userFromDb.getPassword())){
            return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
        }
        session.setAttribute("name", user.getName());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User getUser (HttpSession session){
        String name = (String) session.getAttribute("name");
        return users.findFirstByName(name);
    }

    @RequestMapping(path = "/shop", method = RequestMethod.POST)
    public ResponseEntity<Shop> addShop (HttpSession session, @RequestBody Shop shop){
        String name = (String) session.getAttribute("name");
        if (name == null){
            return new ResponseEntity<Shop>(HttpStatus.FORBIDDEN);
        }
        shop.setUser(users.findFirstByName(name));
        return new ResponseEntity<Shop>(shops.save(shop), HttpStatus.OK);
    }

    @RequestMapping(path = "/edit-shop", method = RequestMethod.POST)
    public ResponseEntity<Shop> editShop (HttpSession session, @RequestBody Shop shop){
        String name = (String) session.getAttribute("name");
        if (name == null){
            return new ResponseEntity<Shop>(HttpStatus.FORBIDDEN);
        }
        if (!shops.findOne(shop.getId()).getUser().getName().equals(name)){
            return new ResponseEntity<Shop>(HttpStatus.FORBIDDEN);
        }
        shop.setUser(users.findFirstByName(name));
        return new ResponseEntity<Shop>(shops.save(shop), HttpStatus.OK);
    }

    @RequestMapping(path = "/delete-shop", method = RequestMethod.POST)
    public ResponseEntity<Shop> deleteShop (HttpSession session, @RequestBody Shop shop){
        String name = (String) session.getAttribute("name");
        if (name == null){
            return new ResponseEntity<Shop>(HttpStatus.FORBIDDEN);
        }
        if (!shops.findOne(shop.getId()).getUser().getName().equals(name)){
            return new ResponseEntity<Shop>(HttpStatus.FORBIDDEN);
        }
        shops.delete(shop.getId());
        return new ResponseEntity<Shop>(HttpStatus.OK);
    }

    @RequestMapping(path = "/like-shop", method = RequestMethod.POST)
    public ResponseEntity<Shop> likeShop (HttpSession session, @RequestBody HashMap like){
        String name = (String) session.getAttribute("name");
        if (name == null){
            return new ResponseEntity<Shop>(HttpStatus.FORBIDDEN);
        }

        Integer id = (Integer) like.get("shopId");
        Boolean likedShop = (Boolean) like.get("likedShop");
        if (id == null || likedShop == null){
            return new ResponseEntity<Shop>(HttpStatus.FORBIDDEN);
        }
        Shop shop = shops.findOne(id);
        shop.setLikes(shop.getLikes() + (likedShop ? 1 : -1));
        shops.save(shop);

        return new ResponseEntity<Shop>(shop, HttpStatus.OK);
    }

    @RequestMapping(path = "/shop", method = RequestMethod.GET)
    public List<Shop> getShops (HttpSession session) {
         return shops.findAllByOrderByLikesDesc();
    }
}


