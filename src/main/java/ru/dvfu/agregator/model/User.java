package ru.dvfu.agregator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by nesud on 27.11.2016.
 */
@Entity
public class User {
    @Getter
    @Setter
    @Id
    private String name;

    @Getter
    @Setter
    private String password;

//    @Getter
//    @Setter
//    private Set<Hub> hubs;
//    @Getter
//    @Setter
//    private Map<Hub, Integer> hubDistribution;
//    @Getter
//    @Setter
//    private List<Article> staredArticles;
//    @Getter
//    @Setter
//    private List<Article> watchLaterArticles;

    public User(String login, String pass) {
        this.name = login;
        this.password = pass;
//        this.hubs = new HashSet<>();
//        this.hubDistribution = new TreeMap<>();
    }
}
