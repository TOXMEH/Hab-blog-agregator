package ru.dvfu.agregator.service;

import org.springframework.stereotype.Service;
import ru.dvfu.agregator.model.User;

/**
 * Created by Anton Nesudimov on 12.11.2016.
 */
@Service
public class AuthorizationService {

    private UserService userService;

    public boolean register(String login, String password) {
        if (userService.getUserByName(login) == null) {
            User user = new User(login, password);
            userService.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean authorize(String login, String password) {
        return userService.getUserByName(login) != null;
    }
}
