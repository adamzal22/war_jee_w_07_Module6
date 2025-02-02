package pl.coderslab.SpringHibernateModul6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateModul6.entity.Author;
import pl.coderslab.SpringHibernateModul6.entity.Book;
import pl.coderslab.SpringHibernateModul6.entity.Category;
import pl.coderslab.SpringHibernateModul6.entity.Publisher;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findFirstByTitle(String title);

    List<Book> findAllByCategory(Category category);

    List<Book> findAllByCategory_Id(long categoryId);

    List<Book> findByAuthors(Author author);

    List<Book> findByPublisher(Publisher publisher);

    List<Book> findByRating(int rating);

    Book findTopByCategoryOrderByTitle(Category category);

}
