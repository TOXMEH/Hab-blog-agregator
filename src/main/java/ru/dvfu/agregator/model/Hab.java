package ru.dvfu.agregator.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by Anton Nesudimov on 16.11.2016.
 */
@Entity
public class Hab {

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "habs")
    private List<Article> articles;
}
