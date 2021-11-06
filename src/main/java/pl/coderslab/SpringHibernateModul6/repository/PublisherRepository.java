package pl.coderslab.SpringHibernateModul6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateModul6.entity.Publisher;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    List<Publisher> findByNip(String nip);

    List<Publisher> findByRegon(String regon);

}
