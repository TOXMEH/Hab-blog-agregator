package ru.dvfu.agregator.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by anton on 15.03.17.
 */
@Controller
@EnableAutoConfiguration
public class LoginController {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(LoginController.class, args);
    }

    @RequestMapping("/auth")
    public String indexAction(ModelMap model, @RequestParam("login") String login) {
        model.addAttribute("login", login);
        return "index";
    }
}