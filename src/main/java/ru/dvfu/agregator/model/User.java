package ru.dvfu.agregator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by nesud on 27.11.2016.
 */
@Entity
public class User {
    @Id
    private String name;

    private String password;
}
