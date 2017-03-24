package ru.dvfu.agregator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.agregator.model.Writer;

/**
 * Created by anton on 15.03.17.
 */
@Repository
public interface WriterRepository extends CrudRepository<Writer, String> {

    Writer getWriterByName(String name);
}

