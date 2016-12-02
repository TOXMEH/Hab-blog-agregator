package ru.dvfu.agregator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.dvfu.agregator.config.SpringConfiguration;
import ru.dvfu.agregator.model.Article;
import ru.dvfu.agregator.model.User;
import ru.dvfu.agregator.service.ArticleService;
import ru.dvfu.agregator.service.UserService;

import java.util.List;

/**
 * Created by nesud on 02.12.2016.
 */
@EnableWebMvc
@RestController
@RequestMapping("/api")
@Api(value = "Articles", description = "controller for getting articles")
public class ArticleController {

    private SpringConfiguration springConfiguration;

    private ArticleService articleService;
    private UserService userService;


    @ApiOperation(value = "user info", notes = "", tags = {"Articles"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 424, message = "USer not having enough hubs")})
    @RequestMapping(value = "/{name}/articles", method = RequestMethod.GET)
    public ResponseEntity<List<Article>> getUser(@PathVariable(value = "name", required = true) String name) {
        User user = userService.getUserByName(name);
        List<Article> articles = articleService.generateArticlesForUser(user);
        if (articles != null) {
            return ResponseEntity.status(HttpStatus.OK).body(articles);
        } else {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(null);
        }
    }
}
