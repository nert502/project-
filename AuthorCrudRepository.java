package domainLayer.repositoty;

import org.springframework.data.repository.CrudRepository;
import domainLayer.entities.Author;

public interface AuthorCrudRepository extends CrudRepository<Author, Integer> {}


 