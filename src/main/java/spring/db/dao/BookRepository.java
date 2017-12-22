package spring.db.dao;

import org.springframework.data.repository.CrudRepository;
import spring.db.model.Book;

/**
 * Created by Sonad on 29.11.17.
 */
public interface BookRepository extends CrudRepository<Book, Integer>  {

    Book findByTitle(String title);

}
