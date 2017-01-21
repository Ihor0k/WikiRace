package ua.ihor0k.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
