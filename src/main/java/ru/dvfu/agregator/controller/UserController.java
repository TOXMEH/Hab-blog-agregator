package ru.dvfu.agregator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.dvfu.agregator.model.Hub;
import ru.dvfu.agregator.model.User;
import ru.dvfu.agregator.service.HubService;
import ru.dvfu.agregator.service.UserService;

import java.util.List;

/**
 * Created by nesud on 02.12.2016.
 */
@EnableWebMvc
@RestController
@RequestMapping("/api/user")
@Api(value = "Articles", description = "controller for getting articles")
public class UserController {

    private UserService userService;
    private HubService hubService;

    @ApiOperation(value = "user info", notes = "", tags = {"User settings"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public User getUser(@PathVariable(value = "name", required = true) String name) {
        return userService.getUserByName(name);
    }

    @ApiOperation(value = "authorize user", notes = "", tags = {"User settings"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "/{name}/hubs", method = RequestMethod.PUT)
    public void setHubs(@PathVariable(value = "name", required = true) String name, @RequestParam(value = "hubs", required = true) List<String> hubs) {
        User user = userService.getUserByName(name);
        for (String hubName : hubs) {
            Hub hub = hubService.getHubByName(hubName);
            user.getHubs().add(hub);
        }
        userService.save(user);
    }
}
