package ua.ihor0k.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.ihor0k.model.User;

public interface UserService extends UserDetailsService {
    void createUser(User user);

    void updateUser(User user);
}
