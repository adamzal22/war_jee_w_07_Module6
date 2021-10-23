package pl.coderslab.SpringHibernateModul6.entity;


import javax.persistence.*;

/**
 * Zadanie 1 - rozwiązywane z wykładowcą
 * W projekcie Spring01hibernate utwórz encje o nazwie Book.
 * Ustal nazwę tabeli bazy danych dla tej encji na books.
 * Encja ma zawierać następujące pola:
 * id
 * title (String)
 * rating (int)
 * description (String)
 * Uruchom aplikację, a następnie sprawdź, czy w bazie danych pojawiła się tabela.
 *
 */
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int rating;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}
