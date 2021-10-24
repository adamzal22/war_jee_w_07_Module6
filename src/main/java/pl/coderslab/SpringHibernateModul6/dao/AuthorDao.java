package pl.coderslab.SpringHibernateModul6.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateModul6.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Author author) {
        entityManager.persist(author);
    }

    public Author merge(Author author) {
        return entityManager.merge(author);
    }

    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    public void remove(long id) {
        Author author = findById(id);
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }

    public List<Author> findAll() {
        Query query = entityManager.createQuery("SELECT a from Author a");
        return query.getResultList();
    }

}
