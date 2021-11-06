package pl.coderslab.SpringHibernateModul6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateModul6.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
