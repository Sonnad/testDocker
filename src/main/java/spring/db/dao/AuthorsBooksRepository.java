package spring.db.dao;

import org.springframework.data.repository.CrudRepository;
import spring.db.model.AuthorsBooks;

/**
 * Created by Sonad on 29.11.17.
 */
public interface AuthorsBooksRepository extends CrudRepository<AuthorsBooks, AuthorsBooks.UserPK>  {

}
