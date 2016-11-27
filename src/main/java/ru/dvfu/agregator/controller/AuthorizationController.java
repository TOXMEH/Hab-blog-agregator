package ru.dvfu.agregator.controller;

import io.swagger.annotations.*;
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
@Api(value = "Authorization", description = "controller for registration and authorization")
public class AuthorizationController {

    @Autowired
    private SpringConfiguration springConfiguration;

    @Autowired
    private AuthorizationService authorizationService;

    @ApiOperation(value = "registrate new user", notes = "make distribution to all bot users", tags = {"Authorization"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")})
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> registrateUser(@ApiParam(value = "user login", required = true) @RequestParam("login") String login, @ApiParam(value = "user password", required = true) @RequestParam("password") String password) {
        String token = authorizationService.register(login, password);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }


    @ApiOperation(value = "make distribition", notes = "make distribution to all bot users", tags = {"Authorization"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "")})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> authorizeUser(@ApiParam(value = "user login", required = true) @RequestParam("login") String login, @ApiParam(value = "user password", required = true) @RequestParam("password") String password) {
        String token = authorizationService.authorize(login, password);
        if (token != null) {
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
