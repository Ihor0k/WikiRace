package ua.ihor0k.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ua.ihor0k.model.User;

@Controller
public class UserController {

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

//    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void register(User user) {
        System.out.println(user.getUsername() + ' ' + user.getPassword());
    }
}
