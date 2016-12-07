package ru.dvfu.agregator.service;

import org.springframework.stereotype.Service;
import ru.dvfu.agregator.model.Article;
import ru.dvfu.agregator.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nesud on 02.12.2016.
 */
@Service
public class ArticleService {
    public List<Article> generateArticlesForUser(User user) {

//        if (user.getHubs().size() == 0) {
//            return null;
//        }
        /*
        * сложный алгоритм
         */
        List<Article> articles = new ArrayList<>();

//        Map<Hub, Integer> hubDistribution = user.getHubDistribution();

//        Map.Entry<Hub, Integer> minHub = Collections.min(hubDistribution.entrySet(),
//                Comparator.comparingDouble(Map.Entry::getValue));

        return null;
    }
}
