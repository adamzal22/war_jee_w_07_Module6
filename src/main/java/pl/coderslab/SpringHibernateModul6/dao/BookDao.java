package pl.coderslab.SpringHibernateModul6.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateModul6.entity.Author;
import pl.coderslab.SpringHibernateModul6.entity.Book;
import pl.coderslab.SpringHibernateModul6.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

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

    public List<Book> findAll() {
        Query query = entityManager.createQuery("SELECT b FROM Book b");
        return query.getResultList();
    }

    public List<Book> findAllByRating(int rating) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating = :givenRating");
        query.setParameter("givenRating", rating);
        return query.getResultList();
    }

    public List<Book> findAllWithPublisher() {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher is not null");
        return query.getResultList();
    }

    public List<Book> findAllByPublisher(Publisher publisher) {
        long publisherId = publisher.getId();

        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher = :pub");
        query.setParameter("pub", publisher);

        Query query1 = entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher.id = :publisherId");
        query1.setParameter("publisherId", publisherId);

        return query1.getResultList();
    }

    public List<Book> findAllByAuthor(Author author) {
        long authorId = author.getId();

        Query query = entityManager.createQuery("SELECT b from Book b join b.authors a where a = :auth");
        query.setParameter("auth", author);

        Query query1 = entityManager.createQuery("SELECT b from Book b join b.authors a where a.id = :authorId");
        query1.setParameter("authorId", authorId);

        return query1.getResultList();
    }

}
