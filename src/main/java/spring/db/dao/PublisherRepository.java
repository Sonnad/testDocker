package spring.db.dao;

import org.springframework.data.repository.CrudRepository;
import spring.db.model.Publisher;

/**
 * Created by Sonad on 29.11.17.
 */
public interface PublisherRepository extends CrudRepository<Publisher, Integer>  {

    Publisher findByName(String name);

}
