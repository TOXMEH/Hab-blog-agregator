package ru.dvfu.agregator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.agregator.model.Reader;

/**
 * Created by nesud on 27.11.2016.
 */
@Repository
public interface ReaderRepository extends CrudRepository<Reader, String> {

    Reader getReaderByName(String name);
}
