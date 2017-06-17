package ru.dvfu.agregator.repository;

/**
 * Created by nesud on 02.12.2016.
 */

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.agregator.model.Hub;

@Repository
public interface HubRepository extends PagingAndSortingRepository<Hub, String> {

    Hub getHubByName(String name);
}
