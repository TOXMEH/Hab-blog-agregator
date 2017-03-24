package ru.dvfu.agregator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anton Nesudimov on 16.11.2016.
 */
@Entity
@NoArgsConstructor
public class Hub {
    @Getter
    @Setter
    @Id
    private String name;


    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private int subscribers = 0;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "hubs")
    private Set<Article> articles;

    public Hub(String name, String description) {
        this.name = name;
        this.description = description;
        this.subscribers = 0;
        this.articles = new HashSet<>();
    }
}
