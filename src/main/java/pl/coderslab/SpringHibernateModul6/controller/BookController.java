package pl.coderslab.SpringHibernateModul6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.SpringHibernateModul6.dao.BookDao;
import pl.coderslab.SpringHibernateModul6.dao.PublisherDao;
import pl.coderslab.SpringHibernateModul6.entity.Book;
import pl.coderslab.SpringHibernateModul6.entity.Publisher;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
    }

    @RequestMapping("/saveWithExistingPublisher")
    @ResponseBody
    public String saveWithExistingPublisher() {
        Publisher publisher = publisherDao.findById(2);
        Book book = new Book();
        book.setTitle("W pustyni i w puszczy");
        book.setDescription("Ksiązka o pustyni i puszczy");
        book.setRating(4);
        book.setPublisher(publisher);
        bookDao.persist(book);
        return book.toString();
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        Publisher publisher = new Publisher();
        publisher.setName("PWN");
        publisherDao.persist(publisher);
        Book book = new Book();
        book.setTitle("W pustyni i w puszczy");
        book.setDescription("Ksiązka o pustyni i puszczy");
        book.setRating(4);
        book.setPublisher(publisher);
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

}
