package spring.db.dao;

import org.springframework.data.repository.CrudRepository;
import spring.db.model.Author;

/**
 * Created by Sonad on 29.11.17.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer>  {

    Author findByFullName(String fullName);

}
