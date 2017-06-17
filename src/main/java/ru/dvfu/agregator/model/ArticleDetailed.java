package ru.dvfu.agregator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by anton on 14.03.17.
 */
@Entity
@Table(name = "article_detailed")
public class ArticleDetailed {

    @Getter
    @Setter
    @Id
    private String header;

    @Setter
    @Getter
    @OneToOne(mappedBy = "articleDetailed")
    private Article article;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "name", nullable = false)
    private Writer writer;

//    @Getter
//    @Setter
//    private int timesStared = 0;
//
//    @Getter
//    @Setter
//    private int timesWatchLater = 0;

    @Getter
    @Setter
    private short publicationYear;

}
