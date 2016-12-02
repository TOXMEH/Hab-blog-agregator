package ru.dvfu.agregator.repository;

/**
 * Created by nesud on 02.12.2016.
 */

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.agregator.model.Hub;
import ru.dvfu.agregator.model.User;

@Repository
public interface HubRepository extends CrudRepository<User, String> {

    Hub getHubByName(String name);
}
