package domainLayer.repositoty;

import org.springframework.data.repository.CrudRepository;
import domainLayer.entities.Book; 

public interface BookCrudRepository extends CrudRepository<Book, Integer> {}  


 
 