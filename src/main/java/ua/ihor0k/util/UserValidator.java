package ua.ihor0k.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.ihor0k.DAO.UserDAO;
import ua.ihor0k.model.User;

@Component
public class UserValidator implements Validator {
    private UserDAO userDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    @Transactional
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "validation.field.notEmpty");
        if (userDAO.getUser(user.getUsername()) != null) {
            errors.rejectValue("username", "validation.username.duplicate");
        }
        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "validation.password.size");
        }
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
