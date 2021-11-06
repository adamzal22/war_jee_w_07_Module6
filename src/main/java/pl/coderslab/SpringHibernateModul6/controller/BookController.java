package pl.coderslab.SpringHibernateModul6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.SpringHibernateModul6.dao.AuthorDao;
import pl.coderslab.SpringHibernateModul6.dao.BookDao;
import pl.coderslab.SpringHibernateModul6.dao.PublisherDao;
import pl.coderslab.SpringHibernateModul6.entity.Author;
import pl.coderslab.SpringHibernateModul6.entity.Book;
import pl.coderslab.SpringHibernateModul6.entity.Category;
import pl.coderslab.SpringHibernateModul6.entity.Publisher;
import pl.coderslab.SpringHibernateModul6.repository.BookRepository;
import pl.coderslab.SpringHibernateModul6.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao, BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/byTitle/{title}")
    @ResponseBody
    public String findByTitleRepository(@PathVariable("title") String title) {
        return bookRepository.findFirstByTitle(title)
                .map(Book::toString)
                .orElse("Ksiazki nie znaleizono o zadanym tytule");
    }

    @GetMapping("/byCat/{idCategory}")
    @ResponseBody
    public String findByCatObject(@PathVariable("idCategory") long idCategory) {
        Category cat = categoryRepository.getById(idCategory);
        return bookRepository.findAllByCategory(cat)
                .stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br />"));
    }

    @GetMapping("/byCategoryId/{idCategory}")
    @ResponseBody
    public String findByCatId(@PathVariable("idCategory") long idCategory) {
        return bookRepository.findAllByCategory_Id(idCategory)
                .stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br />"));
    }


    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        Publisher publisher = publisherDao.findById(3);
        Author firstAuthor = authorDao.findById(2);
        Author secondAuthor = authorDao.findById(3);
        Book book = new Book();
        book.setTitle("W pustyni i w puszczy");
        book.setDescription("Ksiązka o pustyni i puszczy");
        book.setRating(4);
        book.setPublisher(publisher);
        book.getAuthors().add(firstAuthor);
        book.getAuthors().add(secondAuthor);
        bookDao.persist(book);
        return book.toString();
    }

    @RequestMapping("/find/{id}")
    @ResponseBody
    public String findById(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @RequestMapping("/update/{id}/{title}")
    @ResponseBody
    public String update(@PathVariable long id, @PathVariable String title) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.merge(book);
        return book.toString();
    }

    @RequestMapping("/remove/{id}")
    @ResponseBody
    public String remove(@PathVariable long id) {
        bookDao.remove(id);
        return "Usunieto ksiazke";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String findAll() {
        List<Book> allBooks = bookDao.findAll();
        return allBooks.stream()
                .map(book -> book.getId() + " : " + book.getTitle())
                .collect(Collectors.joining("<br />"));
    }

    @RequestMapping("/rating/{rating}")
    @ResponseBody
    public String findAllByRating(@PathVariable int rating) {
        List<Book> allBooksByRating = bookDao.findAllByRating(rating);
        return allBooksByRating.stream()
                .map(book -> book.getId() + " : " + book.getTitle() + " : " + book.getRating())
                .collect(Collectors.joining("<br />"));
    }

    @RequestMapping("/publisher/any")
    @ResponseBody
    public String findWithAnyPublisher() {
        List<Book> allBooksByRating = bookDao.findAllWithPublisher();
        return allBooksByRating.stream()
                .map(book -> book.getId() + " : " + book.getTitle())
                .collect(Collectors.joining("<br />"));
    }

    @RequestMapping("/publisher/{publisherId}")
    @ResponseBody
    public String findByPublisher(@PathVariable long publisherId) {
        Publisher publisher = publisherDao.findById(publisherId);
        List<Book> allBooksByRating = bookDao.findAllByPublisher(publisher);
        return allBooksByRating.stream()
                .map(book -> book.getId() + " : " + book.getTitle())
                .collect(Collectors.joining("<br />"));
    }

    @RequestMapping("/author/{authorId}")
    @ResponseBody
    public String findByAuthor(@PathVariable long authorId) {
        Author author = authorDao.findById(authorId);
        List<Book> allBooksByRating = bookRepository.findByAuthors(author);
        return allBooksByRating.stream()
                .map(book -> book.getId() + " : " + book.getTitle())
                .collect(Collectors.joining("<br />"));
    }


}


