package ru.dvfu.agregator.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Created by Anton Nesudimov on 16.11.2016.
 */
@Entity
public class Article {
    @Id
    @GeneratedValue
    private short id;

    /*
    *  Заголовок
     */
    private String header;

    private String Url;

    @ManyToMany
    @JoinTable(name="article_hub",
            joinColumns = @JoinColumn(name="article_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="hab_name", referencedColumnName="name")
    )
    private List<Hub> hubs;

    /*
    * Год публикации
     */
    @Min(2000)
    @Max(2100)
    private short publicationYear;
}
