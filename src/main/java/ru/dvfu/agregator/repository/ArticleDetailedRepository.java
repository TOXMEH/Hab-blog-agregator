package ru.dvfu.agregator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.agregator.model.ArticleDetailed;

/**
 * Created by nesud on 06.01.2017.
 */
@Repository
public interface ArticleDetailedRepository extends CrudRepository<ArticleDetailed, String> {

}
