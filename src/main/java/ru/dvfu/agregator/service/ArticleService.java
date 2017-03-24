package ru.dvfu.agregator.service;

import org.springframework.stereotype.Service;
import ru.dvfu.agregator.model.Article;
import ru.dvfu.agregator.model.Reader;
import ru.dvfu.agregator.model.Writer;
import ru.dvfu.agregator.repository.ArticleRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by nesud on 02.12.2016.
 */
@Service
public class ArticleService {

    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private HubService hubService;
    @Resource
    private WriterService writerService;

    public List<Article> generateArticlesForUser(Reader reader) {

//        if (reader.getHubs().size() == 0) {
//            return null;
//        }
        /*
        * сложный алгоритм
         */
        List<Article> articles = new ArrayList<>();

//        Map<Hub, Integer> hubRating = reader.getHubRating();

//        Map.Entry<Hub, Integer> minHub = Collections.min(hubRating.entrySet(),
//                Comparator.comparingDouble(Map.Entry::getValue));

        return null;
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<Article> getAllArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    public Article addArticle(String header, String text, String writerName, String hubOne, String hubTwo, String hubThree) {
        Writer writer = writerService.getWriterByName(writerName);
        Article article = new Article(header, text, writer);
        if ((!Objects.equals(hubOne, "")) && (hubService.getHubByName(hubOne) != null)) {
            article.getHubs().add(hubService.getHubByName(hubOne));
        }
        if ((!Objects.equals(hubTwo, "")) && (hubService.getHubByName(hubTwo) != null)) {
            article.getHubs().add(hubService.getHubByName(hubTwo));
        }
        if ((!Objects.equals(hubThree, "")) && (hubService.getHubByName(hubThree) != null)) {
            article.getHubs().add(hubService.getHubByName(hubThree));
        }
        return articleRepository.save(article);
    }

    public Article editArticle(String header, String text, String hubOne, String hubTwo, String hubThree) {
        Article article = articleRepository.getArticleByHeader(header);
        article.setText(text);
        article.getHubs().clear();
        if ((!Objects.equals(hubOne, "")) && (hubService.getHubByName(hubOne) != null)) {
            article.getHubs().add(hubService.getHubByName(hubOne));
        }
        if ((!Objects.equals(hubTwo, "")) && (hubService.getHubByName(hubTwo) != null)) {
            article.getHubs().add(hubService.getHubByName(hubTwo));
        }
        if ((!Objects.equals(hubThree, "")) && (hubService.getHubByName(hubThree) != null)) {
            article.getHubs().add(hubService.getHubByName(hubThree));
        }
        return articleRepository.save(article);
    }

    public Article getArticleByHeader(String articleHeader) {
        return articleRepository.getArticleByHeader(articleHeader);
    }
}
