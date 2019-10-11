package com.whosplayin.controllers;

import com.whosplayin.entities.User;


import com.whosplayin.services.UserRepo;
import com.whosplayin.utilities.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Created by rdw1995 on 11/15/16.
 */

@RestController
public class WhosPlayinRestController {


    public final String API_KEY = "YlX4r2ab8xzzlYDB";


    @Autowired
    UserRepo users;

    Server h2;

    @PostConstruct
    public void init() throws SQLException, PasswordStorage.CannotPerformOperationException {
        Server.createWebServer("-webPort", "1338").start();
    // String username, String password, String firstName, String lastName
        if (users.count() == 0){
            User user = new User("teambekbek", PasswordStorage.createHash("iwantMusic2"), "Rebekah", "Whittle");
            users.save(user);
        }
    }

    @PreDestroy
    public void destroy() {
        h2.stop();
    }



    // sign-up for a WhosPlayin account
    @RequestMapping(path = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<User> addUser (HttpSession session, @RequestBody User user) throws PasswordStorage.CannotPerformOperationException, PasswordStorage.InvalidHashException {
        User validUsername = users.findFirstByUsername(user.getUsername());
        if (validUsername == null){
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(user.getPassword(), validUsername.getPassword())){
            return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
        }
        session.setAttribute("username", user.getUsername());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // sign-in for WhosPlayin account
    @RequestMapping(path = "/sign-in", method = RequestMethod.POST)
    public ResponseEntity<User> signIn (HttpSession session, @RequestBody User user) throws Exception {
        User aUser = users.findFirstByUsername(user.getUsername());
        if(aUser == null){
            throw new Exception("Username doesn't exist! Please Sign-Up!");
        }
        else if (!PasswordStorage.verifyPassword(user.getPassword(), aUser.getPassword())){
            throw new Exception("Oops - incorrect password! Try again!");
        }
        session.setAttribute("username", user.getUsername());
        return new ResponseEntity<User>(aUser, HttpStatus.OK);
    }

    // sign-out of WhosPlayin account
    @RequestMapping(path = "/sign-out", method = RequestMethod.POST)
    public void signOut(HttpSession session){
        session.invalidate();
    }

    // displays the signed- (in) user for WhosPlayin
    @RequestMapping(path = "/get-account", method = RequestMethod.GET)
    public User getUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        User inUser = users.findFirstByUsername(username);
        if (inUser == null) {
            return null;
        }
        return inUser;
    }

    // edit or update account information for WhosPlayin
    @RequestMapping(path = "/edit-account", method = RequestMethod.POST)
    public void editAccount (HttpSession session, @RequestBody User user) throws Exception {
        String username = (String) session.getAttribute("username");
        User validUser = users.findFirstByUsername(username);
        if (validUser == null && validUser.getId() != user.getId()){
            throw new Exception("Oops! It looks like you're not signed-in!");
        }
        validUser.setUsername(user.getUsername());
        validUser.setPassword(user.getPassword());
        // PasswordStorage.verifyPassword(^username.getPassword())
        validUser.setCity(user.getCity());
        validUser.setEmail(user.getCity());
        validUser.setFirstName(user.getFirstName());
        validUser.setLastName(user.getLastName());
        validUser.setPhone(user.getPhone());

        users.save(validUser);
    }

    // DELETE WHOSPLAYIN ACCOUNT
    @RequestMapping(path = "/delete-account", method = RequestMethod.POST)
    public void deleteAccount (HttpSession session, @RequestBody User user) throws Exception {
        String username = (String) session.getAttribute("username");
        User validUser = users.findFirstByUsername(username);
        if(!PasswordStorage.verifyPassword(user.getPassword(), validUser.getPassword())){
            throw new Exception("Sesame cannot open - please try again!");
        }
        users.delete(validUser);
    }

    // REQUEST LOCATION AS A STRING -- GRAB METROID
    @RequestMapping(path = "/location={location}", method = RequestMethod.GET)
    public ResponseEntity <Integer>  getLocation(@PathVariable("location") String location) {
        String request = "http://api.songkick.com/api/3.0/search/locations.json?";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request)
                .queryParam("query", location)
                .queryParam("apikey", API_KEY);

        RestTemplate query = new RestTemplate();
        HashMap search = query.getForObject(builder.build().encode().toUri(), HashMap.class);
        HashMap resultsPage = (HashMap) search.get("resultsPage");
        HashMap results = (HashMap) resultsPage.get("results");
        ArrayList locationA = (ArrayList) results.get("location");
        HashMap info =  (HashMap) locationA.get(0);
        HashMap metroArea = (HashMap) info.get("metroArea");
        int metroAreaId = (int) metroArea.get("id");
        return new ResponseEntity<Integer>(metroAreaId, HttpStatus.OK);
    }

    // REQUEST ALL EVENTS BASED OFF OF AREA ID
    @RequestMapping(path = "/events-calendar/{areaId}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList> getEvents(@PathVariable("areaId") int areaId){

        String request = "http://api.songkick.com/api/3.0/metro_areas/" + areaId + "/calendar.json";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request)
                .queryParam("apikey", API_KEY);

        RestTemplate query = new RestTemplate();
        HashMap search = query.getForObject(builder.build().encode().toUri(), HashMap.class);
        HashMap resultsPage = (HashMap) search.get("resultsPage");
        HashMap results = (HashMap) resultsPage.get("results");
        ArrayList event = (ArrayList) results.get("event");
        return new ResponseEntity<ArrayList>(event, HttpStatus.OK);
    }

    // COMBINES ABOVE METHODS TO RUN SUCCINCTLY
        // call integer from method and return the event with id inserted
    @RequestMapping(path = "/whosplayin/{location}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList> whosplayin(@PathVariable ("location") String location){
        int metroAreaId = getLocation(location).getBody();
        return getEvents(metroAreaId);
    }


    // RETURNS ARRAY OF ARTISTS FOR JORDAN
    @RequestMapping(path = "/whosplayin/search/artist={artist}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList> getArtistArray (@PathVariable("artist") String artist){

        String request = "http://api.songkick.com/api/3.0/search/artists.json?";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request)
                .queryParam("query", artist)
                .queryParam("apikey", API_KEY);

        RestTemplate query = new RestTemplate();
        HashMap search = query.getForObject(builder.build().encode().toUri(), HashMap.class);
        HashMap resultsPage = (HashMap) search.get("resultsPage");
        HashMap results = (HashMap) resultsPage.get("results");
        ArrayList artistA = (ArrayList) results.get("artist");
        return new ResponseEntity<ArrayList>(artistA, HttpStatus.OK);
    }

    // SEARCH FOR ARTIST ID
    @RequestMapping(path = "/whosplayin/id=artist={artist}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getArtistId (@PathVariable("artist") String artist){

        String request = "http://api.songkick.com/api/3.0/search/artists.json?";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request)
                .queryParam("query", artist)
                .queryParam("apikey", API_KEY);

        RestTemplate query = new RestTemplate();
        HashMap search = query.getForObject(builder.build().encode().toUri(), HashMap.class);
        HashMap resultsPage = (HashMap) search.get("resultsPage");
        HashMap results = (HashMap) resultsPage.get("results");
        ArrayList artistA = (ArrayList) results.get("artist");
        HashMap info = (HashMap) artistA.get(0);
        int artistId = (int) info.get("id");

        return new ResponseEntity<Integer>(artistId, HttpStatus.OK);
    }

    // SEE ARTISTS CALENDAR
        // 197928 Coldplay
    @RequestMapping(path = "/whosplayin/calendar/{artistId}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList> getArtistCalendar(@PathVariable("artistId") int artistId){

        String request = "http://api.songkick.com/api/3.0/artists/" + artistId +"/calendar.json";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request)
                .queryParam("apikey", API_KEY);

        RestTemplate query = new RestTemplate();
        HashMap search = query.getForObject(builder.build().encode().toUri(), HashMap.class);
        HashMap resultsPage = (HashMap) search.get("resultsPage");
        HashMap results = (HashMap) resultsPage.get("results");
        ArrayList events = (ArrayList) results.get("event");
        return new ResponseEntity<ArrayList>(events, HttpStatus.OK);
    }

        //     COMBINE BOTH ARTIST METHODS TO GET ARTIST CALENDAR
            //   USE ARTISTID TO RETURN CALENDAR WITH ARTIST INSERTED
    @RequestMapping(path = "/whosplayin/{artist}=calendar", method = RequestMethod.GET)
    public ResponseEntity<ArrayList> artistCalendar(@PathVariable("artist") String artist){
        int artistId = getArtistId(artist).getBody();
        return getArtistCalendar(artistId);
    }

    // SEARCH FOR VENUES
    @RequestMapping(path = "/whosplayin/search/venue={venue}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getVenueId (@PathVariable("venue") String venue) {
        String request = "http://api.songkick.com/api/3.0/search/venues.json";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request)
                .queryParam("query", venue)
                .queryParam("apikey", API_KEY);

        RestTemplate query = new RestTemplate();
        HashMap search = query.getForObject(builder.build().encode().toUri(), HashMap.class);
        HashMap resultsPage = (HashMap) search.get("resultsPage");
        HashMap results = (HashMap) resultsPage.get("results");
        ArrayList venueA = (ArrayList) results.get("venue");
        HashMap info = (HashMap) venueA.get(0);
        int venueId = (int) info.get("id");

        return new ResponseEntity<Integer>(venueId, HttpStatus.OK);
    }

    // SEE CALENDAR OF THIS VENUE
    @RequestMapping(path = "/whosplayin/calendar={venueId}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList> getVenueCalendar(@PathVariable("venueId") int venueId){

        String request = "http://api.songkick.com/api/3.0/venues/" + venueId +"/calendar.json";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request)
                .queryParam("apikey", API_KEY);

        RestTemplate query = new RestTemplate();
        HashMap search = query.getForObject(builder.build().encode().toUri(), HashMap.class);
        HashMap resultsPage = (HashMap) search.get("resultsPage");
        HashMap results = (HashMap) resultsPage.get("results");
        ArrayList events = (ArrayList) results.get("event");

        return new ResponseEntity<ArrayList>(events, HttpStatus.OK);
    }

    // COMBINE ABOVE METHODS TO VIEW CALENDAR OF EVENTS FOR SPECIFIC VENUE
        // GRAB VENUEID AND RETURN IT FOR THE CALENDAR
    @RequestMapping(path = "/whosplayin/{venue}=upcoming-events", method = RequestMethod.GET)
    public ResponseEntity<ArrayList> venueCalendar (@PathVariable("venue") String venue){
        int venueId = getVenueId(venue).getBody();
        return getVenueCalendar(venueId);
    }
}
