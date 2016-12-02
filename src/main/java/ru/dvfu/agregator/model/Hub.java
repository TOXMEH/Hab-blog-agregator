package ru.dvfu.agregator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by Anton Nesudimov on 16.11.2016.
 */
@Entity
public class Hub {
    @Getter
    @Setter
    @Id
    private String name;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "hubs")
    private List<Article> articles;

    public Hub(String name) {
        this.name = name;
    }
}
