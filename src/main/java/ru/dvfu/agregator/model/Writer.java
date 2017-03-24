package ru.dvfu.agregator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anton on 14.03.17.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Writer {
    @Getter
    @Setter
    @Id
    private String name;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "writer")
    private Set<ArticleDetailed> articles;

    public Writer(String name, String password) {
        this.name = name;
        this.password = password;
        this.articles = new HashSet<>();
    }
}
