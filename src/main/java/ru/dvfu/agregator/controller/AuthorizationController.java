package ru.dvfu.agregator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.dvfu.agregator.config.SpringConfiguration;
import ru.dvfu.agregator.service.AuthorizationService;

/**
 * Created by Anton Nesudimov on 12.11.2016.
 */
@EnableWebMvc
@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    @Autowired
    private SpringConfiguration springConfiguration;

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> registrateUser(@RequestParam("login") String login, @RequestParam("password") String password) {
        String token = authorizationService.register(login, password);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
