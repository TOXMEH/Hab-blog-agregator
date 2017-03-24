package ru.dvfu.agregator.controller;


import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.dvfu.agregator.model.Article;
import ru.dvfu.agregator.model.ArticleDetailed;
import ru.dvfu.agregator.model.Hub;
import ru.dvfu.agregator.model.Reader;
import ru.dvfu.agregator.service.ArticleService;
import ru.dvfu.agregator.service.ReaderService;
import ru.dvfu.agregator.service.WriterService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nesud on 02.12.2016.
 */
@EnableWebMvc
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Resource
    private ArticleService articleService;
    @Resource
    private WriterService writerService;
    @Resource
    private ReaderService readerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<JSONObject> getAllArticles() {
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (Article article : articleService.getAllArticles()) {
            JSONObject articleObject = new JSONObject();
            articleObject.put("header", article.getHeader());
            articleObject.put("text", article.getText());
            articleObject.put("writer", article.getArticleDetailed().getWriter().getName());
            articleObject.put("publicationYear", article.getArticleDetailed().getPublicationYear());
            articleObject.put("timesStared", article.getArticleDetailed().getTimesStared());
            articleObject.put("timesWatchLater", article.getArticleDetailed().getTimesWatchLater());
            Hub[] hubs = new Hub[0];
            hubs = article.getHubs().toArray(hubs);
            articleObject.put("hubOne", hubs[0].getName());
            if (hubs[1] != null) {
                articleObject.put("hubTwo", hubs[1].getName());
            }
            if (hubs[2] != null) {
                articleObject.put("hubThree", hubs[2].getName());
            }
            jsonObjects.add(articleObject);
        }
        return jsonObjects;
    }

    @RequestMapping(value = "/writer/{login}", method = RequestMethod.GET)
    public List<JSONObject> getArticlesOfWriter(@PathVariable("login") String writerName) {
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (ArticleDetailed articleDetailed : writerService.getWriterByName(writerName).getArticles()) {
            JSONObject articleObject = new JSONObject();
            articleObject.put("header", articleDetailed.getHeader());
            articleObject.put("text", articleDetailed.getArticle().getText());
            articleObject.put("publicationYear", articleDetailed.getPublicationYear());
            articleObject.put("timesStared", articleDetailed.getTimesStared());
            articleObject.put("timesWatchLater", articleDetailed.getTimesWatchLater());
            Hub[] hubs = new Hub[0];
            hubs = articleDetailed.getArticle().getHubs().toArray(hubs);
            articleObject.put("hubOne", hubs[0].getName());
            if ((hubs.length > 1) && (hubs[1] != null)) {
                articleObject.put("hubTwo", hubs[1].getName());
            }
            if ((hubs.length > 2) && (hubs[2] != null)) {
                articleObject.put("hubThree", hubs[2].getName());
            }
            jsonObjects.add(articleObject);
        }
        return jsonObjects;
    }

    @RequestMapping(value = "/reader/{login}", method = RequestMethod.GET)
    public Set<JSONObject> getArticlesOfReader(@PathVariable("login") String readerName) {
        Set<JSONObject> jsonObjects = new HashSet<>();
        Reader reader = readerService.getReaderByName(readerName);
        for (Hub hub : reader.getHubs()) {
            for (Article article : hub.getArticles()) {
                JSONObject articleObject = new JSONObject();
                articleObject.put("header", article.getHeader());
                articleObject.put("text", article.getText());
                if (reader.getStaredArticles().contains(article.getArticleDetailed())) {
                    articleObject.put("stared", true);
                }
                if (reader.getWatchLaterArticles().contains(article.getArticleDetailed())) {
                    articleObject.put("later", true);
                }
                Hub[] hubs = new Hub[0];
                hubs = article.getHubs().toArray(hubs);
                articleObject.put("hubOne", hubs[0].getName());
                if ((hubs.length > 1) && (hubs[1] != null)) {
                    articleObject.put("hubTwo", hubs[1].getName());
                }
                if ((hubs.length > 2) && (hubs[2] != null)) {
                    articleObject.put("hubThree", hubs[2].getName());
                }
                jsonObjects.add(articleObject);
            }
        }
        return jsonObjects;
    }

    @RequestMapping(value = "/stare", method = RequestMethod.POST)
    public void stareArticle(@RequestParam("reader") String readerName, @RequestParam("article") String articleHeader) {
        Reader reader = readerService.getReaderByName(readerName);
        Article article = articleService.getArticleByHeader(articleHeader);
        reader.getStaredArticles().add(article.getArticleDetailed());
        article.getArticleDetailed().setTimesStared(article.getArticleDetailed().getTimesStared() + 1);
    }

    @RequestMapping(value = "/later", method = RequestMethod.POST)
    public void laterArticle(@RequestParam("header") String header, @RequestParam("text") String text, @RequestParam("writer") String writerName, @RequestParam("hubOne") String hubOne, @RequestParam("hubTwo") String hubTwo, @RequestParam("hubThree") String hubThree) {
        articleService.addArticle(header, text, writerName, hubOne, hubTwo, hubThree);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addArticle(@RequestParam("header") String header, @RequestParam("text") String text, @RequestParam("writer") String writerName, @RequestParam("hubOne") String hubOne, @RequestParam("hubTwo") String hubTwo, @RequestParam("hubThree") String hubThree) {
        articleService.addArticle(header, text, writerName, hubOne, hubTwo, hubThree);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void editArticle(@RequestParam("header") String header, @RequestParam("text") String text, @RequestParam("hubOne") String hubOne, @RequestParam("hubTwo") String hubTwo, @RequestParam("hubThree") String hubThree) {
        articleService.editArticle(header, text, hubOne, hubTwo, hubThree);
    }
}
