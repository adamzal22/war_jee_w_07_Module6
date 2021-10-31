package pl.coderslab.SpringHibernateModul6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateModul6.dao.PublisherDao;
import pl.coderslab.SpringHibernateModul6.entity.Publisher;

@Controller
@RequestMapping("/publisher/form")
public class PublisherFormController {

    private final PublisherDao publisherDao;

    public PublisherFormController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("publishers", publisherDao.findAll());
        return "/publisher/publisherListing";
    }

    @GetMapping("/add")
    public String preparePublisherAdd(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "/publisher/publisherForm";
    }

    @PostMapping("/add")
    public String savePublisher(@ModelAttribute("publisher") Publisher publisher) {
        publisherDao.persist(publisher);
        return "redirect:/publisher/form/all";
    }

    @GetMapping("/edit")
    public String prepareEdit(@RequestParam int idToEdit, Model model) {
        model.addAttribute("publisher", publisherDao.findById(idToEdit));
        return "/publisher/publisherForm";
    }

    @PostMapping("/edit")
    public String merge(@ModelAttribute("publisher") Publisher publisher) {
        publisherDao.merge(publisher);
        return "redirect:/publisher/form/all";
    }

    @GetMapping("/remove")
    public String prepareRemove(@RequestParam int toRemoveId, Model model) {
        model.addAttribute("publisher", publisherDao.findById(toRemoveId));
        return "/publisher/remove";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam String confirmed, @RequestParam int toRemoveId) {
        if ("yes".equals(confirmed)) {
            publisherDao.remove(toRemoveId);
        }
        return "redirect:/publisher/form/all";
    }

}
