package ua.ihor0k.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.ihor0k.model.User;
import ua.ihor0k.service.UserService;

@Component
public class UserValidator implements Validator {
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "validation.field.notEmpty");
        if (userService.loadUserByUsername(user.getUsername())!=null)
            errors.rejectValue("username", "validation.username.duplicate");
        if (user.getPassword().length() < 6)
            errors.rejectValue("password", "validation.password.size");
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
