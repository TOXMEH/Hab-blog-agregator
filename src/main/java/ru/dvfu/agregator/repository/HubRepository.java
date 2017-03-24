package ru.dvfu.agregator.repository;

/**
 * Created by nesud on 02.12.2016.
 */

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.agregator.model.Hub;

@Repository
public interface HubRepository extends CrudRepository<Hub, String> {

    Hub getHubByName(String name);
}
