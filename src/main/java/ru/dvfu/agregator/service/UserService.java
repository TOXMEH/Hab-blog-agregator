package ru.dvfu.agregator.service;

import org.springframework.stereotype.Service;
import ru.dvfu.agregator.model.User;
import ru.dvfu.agregator.repository.UserRepository;

/**
 * Created by nesud on 27.11.2016.
 */
@Service
public class UserService {

    private UserRepository userRepository;

    public User getUserByName(String name) {
        return null;
    }

    public void save(User mockUser) {

    }
}
