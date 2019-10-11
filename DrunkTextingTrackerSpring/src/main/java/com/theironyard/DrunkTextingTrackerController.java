package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by rdw1995 on 10/21/16.
 */
@Controller
public class DrunkTextingTrackerController {

@Autowired
    MessageRepository messages;
@Autowired
    UserRepository users;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home (Model model, Integer id, HttpSession session, String search){
        String username = (String)session.getAttribute("username");
        if(username == null){
            return "login";
        }
        else {
            if (search != null ) {
                model.addAttribute("messages",messages.findByAuthorOrTextOrDrunk(search, search, search));
            }
            else {
                Iterable<Message> mlist = messages.findAll();
                if (id != null) {
                    Message m = messages.findOne(id);
                    model.addAttribute("edit", m);
                }
                model.addAttribute("messages", mlist);
            }
            model.addAttribute("username", username);
            return "home";
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
        public String login(String username, String password, HttpSession session){
            User user = users.findByUsername(username);

            if (user == null){
                user = new User(username, password);
                users.save(user);
            }
            else if (!user.password.equals(password)){
                return "redirect:/";
            }

            session.setAttribute("username",username);
            return "redirect:/";
    }


    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage(String mtext, String mauth, String drunk, String mimage, HttpSession session){
        String username = (String)session.getAttribute("username");
        User user = users.findByUsername(username);

        Message m = new Message(mtext, mauth, drunk, mimage);
        messages.save(m);
        return "redirect:/";
    }


    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteM (int id){
        messages.delete(id);
        return "redirect:/";
    }


    @RequestMapping(path = "/edit-message", method = RequestMethod.POST)
    public String editM (int id, String mtext, String mauth, String drunk, String mimage, HttpSession session) {
        String username = (String)session.getAttribute("username");
        User user = users.findByUsername(username);
        Message m = new Message(id, mtext, mauth, drunk, mimage);
        messages.save(m);
        return "redirect:/";
    }


    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}