package pl.coderslab.SpringHibernateModul6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.SpringHibernateModul6.dao.PublisherDao;
import pl.coderslab.SpringHibernateModul6.entity.Publisher;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
    
    private final PublisherDao publisherDao;

    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @RequestMapping("/save")
    @ResponseBody
    public String persist() {
        Publisher publisher = new Publisher();
        publisher.setName("PWN");
        publisherDao.persist(publisher);
        return publisher.toString();
    }

    @RequestMapping("/find/{id}")
    @ResponseBody
    public String findById(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        return publisher.toString();
    }

    @RequestMapping("/update/{id}/{name}")
    @ResponseBody
    public String merge(@PathVariable long id, @PathVariable String name) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(name);
        publisherDao.merge(publisher);
        return publisher.toString();
    }

    @RequestMapping("/remove/{id}")
    @ResponseBody
    public String remove(@PathVariable long id) {
        publisherDao.remove(id);
        return "Usunieto wydawce";
    }
    
}
