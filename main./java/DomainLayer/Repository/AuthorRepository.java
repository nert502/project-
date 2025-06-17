package domainLayer.repositoty;


import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import domainLayer.entities.Author;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Repository
public class AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;
    
    private List<Author> authors = new ArrayList<>(); 

    public List<Author> get() {
        List<Author> result = entityManager
                .createQuery("SELECT a FROM Author a", Author.class)
                .getResultStream()
                .filter(a -> a instanceof Author)
                .map(a -> (Author) a)
                .collect(Collectors.toList());

        return result;
    }

    public Author getById(int id) {
        return entityManager.find(Author.class, id);
    }

    public int create(Author a) {
        entityManager
                .createNativeQuery("INSERT INTO author (name, nationality, birth_date) VALUES (?, ?, ?)")
                .setParameter(1, a.getName())   
                .setParameter(2, a.getNationality())
                .setParameter(3, a.getBirthDate())
                .executeUpdate();

        int authorId = ((Number) entityManager
                .createNativeQuery("SELECT id FROM author WHERE name = ? AND nationality = ? AND birth_date = ?")
                .setParameter(1, a.getName())
                .setParameter(2, a.getNationality())
                .setParameter(3, a.getBirthDate())
                .getSingleResult()).intValue();

        return authorId;
    }

    public Author update(Author a) {
        Author existing = getById(a.getId()); 
        existing.setName(a.getName());
        existing.setNationality(a.getNationality());
        existing.setBirthDate(a.getBirthDate());
        entityManager.flush();
        return existing;
    }

    

	public void delete(int id) {
        Author a = getById(id);
        entityManager.remove(a);
    }
	
	
	public List<Author> findAll() {
        return authors;
    }
}

