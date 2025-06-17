package domainLayer.repositoty;


import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import domainLayer.entities.Book;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;
    
    private List<Book> books = new ArrayList<>();

    public List<Book> get() {
        List<Book> result = entityManager
                .createQuery("SELECT b FROM Book b", Book.class)
                .getResultStream()
                .filter(b -> b instanceof Book)
                .map(b -> (Book) b)
                .collect(Collectors.toList());

        return result;
    }

    public Book getById(int id) {
        return entityManager.find(Book.class, id);
    }

    public int create(Book book) {
        entityManager
                .createNativeQuery("INSERT INTO book (title, category, author_id) VALUES (?, ?, ?)")
                .setParameter(1, book.getTitle())
                .setParameter(2, book.getCategory())
                .setParameter(3, book.getAuthorName())  
                .executeUpdate();

      
        int bookId = ((Number) entityManager
                .createNativeQuery("SELECT b.id FROM book b WHERE b.title = ?")
                .setParameter(1, book.getTitle())
                .getSingleResult()).intValue();

        return bookId;
    }

    public Book update(Book book) {
        Book b = getById(book.getId());

        b.setTitle(book.getTitle());
        b.setCategory(book.getCategory());
        b.setAuthorName(book.getAuthorName());     

        entityManager.flush();
        return b;
    }

    public void delete(int id) {
        Book b = getById(id);
        entityManager.remove(b);
    }
    
    public List<Book> findAll(){
    	return books;   
    }
}
