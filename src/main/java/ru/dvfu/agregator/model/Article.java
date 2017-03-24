package ru.dvfu.agregator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anton Nesudimov on 16.11.2016.
 */
@Entity
@NoArgsConstructor
public class Article {
    /*
    *  Заголовок
     */
    @Getter
    @Setter
    @Id
    private String header;

//    @Getter
//    @Setter
//    private String url;

    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    private String text;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "article_hub",
            joinColumns = @JoinColumn(name = "article_header", referencedColumnName = "header"),
            inverseJoinColumns = @JoinColumn(name = "hub_name", referencedColumnName = "name")
    )
    private Set<Hub> hubs;
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ArticleDetailed articleDetailed;

    public Article(String header, String text, Writer writer) {
        this.header = header;
        this.text = text;
        this.hubs = new HashSet<>();
        this.articleDetailed = new ArticleDetailed();
        this.articleDetailed.setHeader(header);
        this.articleDetailed.setWriter(writer);
        this.articleDetailed.setPublicationYear((short) 2017);
        this.articleDetailed.setTimesStared(0);
        this.articleDetailed.setTimesWatchLater(0);
        this.articleDetailed.setArticle(this);
    }

}
