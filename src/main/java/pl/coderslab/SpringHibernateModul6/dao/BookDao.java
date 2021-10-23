package pl.coderslab.SpringHibernateModul6.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateModul6.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Book book) {
        entityManager.persist(book);
    }

    public Book merge(Book book) {
        return entityManager.merge(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void remove(long id) {
        Book book = findById(id);
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }

}
