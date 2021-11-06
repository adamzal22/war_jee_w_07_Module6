package pl.coderslab.SpringHibernateModul6.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * Stwórz encję Category i połącz ją relacją z Book. Książka ma jedną kategorię.
 * W repozytorium dla klasy Book utwórz metody pobierające:
 * metodę wyszukującą książki dla zadanego tytułu.
 * metodę wyszukującą książki dla zadanej kategorii
 * metodę wyszukującą książki dla zadanego id kategorii
 * */

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
