package pl.coderslab.SpringHibernateModul6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateModul6.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByEmail(String email);

    List<Author> findByPesel(String pesel);

    List<Author> findAllByLastName(String lastName);

}
