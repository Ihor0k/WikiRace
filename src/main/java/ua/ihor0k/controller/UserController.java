package ua.ihor0k.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.ihor0k.model.User;
import ua.ihor0k.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    @RequestMapping(value = "/user/{userName}")
    public String userProfile(@PathVariable("userName") String userName, Model model) {
        User user = (User) userService.loadUserByUsername(userName);
        model.addAttribute("user", user);
        return "profile";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
