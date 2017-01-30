package ua.ihor0k.service;

import ua.ihor0k.model.User;

public interface SecurityService {
    User findLoggedInUser();

    void autologin(String username, String password);
}
