package ru.dvfu.agregator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.agregator.model.Admin;

/**
 * Created by anton on 15.03.17.
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {

    Admin getAdminByName(String name);
}
