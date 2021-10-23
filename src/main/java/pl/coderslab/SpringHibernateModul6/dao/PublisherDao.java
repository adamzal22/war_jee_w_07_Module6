package pl.coderslab.SpringHibernateModul6.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateModul6.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PublisherDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public Publisher merge(Publisher publisher) {
        return entityManager.merge(publisher);
    }

    public Publisher findById(long id) {
        return entityManager.find(Publisher.class, id);
    }

    public void remove(long id) {
        Publisher publisher = findById(id);
        entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }

}
