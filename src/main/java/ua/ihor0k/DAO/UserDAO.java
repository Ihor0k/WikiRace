package ua.ihor0k.DAO;

import ua.ihor0k.model.User;

public interface UserDAO {
    void createUser(User user);

    User getUser(String username);

    void updateUser(User user);
}
