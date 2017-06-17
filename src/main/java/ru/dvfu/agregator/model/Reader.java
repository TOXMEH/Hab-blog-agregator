package ru.dvfu.agregator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nesud on 27.11.2016.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reader {
    @Getter
    @Setter
    @Id
    private String name;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Hub> hubs;
//
//    @Getter
//    @Setter
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<ArticleDetailed> staredArticles;
//
//    @Getter
//    @Setter
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<ArticleDetailed> watchLaterArticles;

    public Reader(String name, String password) {
        this.name = name;
        this.password = password;
        this.hubs = new HashSet<>();
//        this.staredArticles = new HashSet<>();
//        this.watchLaterArticles = new HashSet<>();
    }

    //
////    @Getter
////    @Setter
////    @OneToMany(cascade = CascadeType.ALL)
////    @MapKey(name = "hub_name")
////    private Map<Hub, Integer> hubRating;
//
//    public Reader(String login, String pass) {
//        this.name = login;
//        this.password = pass;
//        this.hubs = new HashSet<>();
////        this.hubRating = new TreeMap<>();
//    }
}
