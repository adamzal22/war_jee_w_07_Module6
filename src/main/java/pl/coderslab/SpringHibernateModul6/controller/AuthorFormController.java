package pl.coderslab.SpringHibernateModul6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateModul6.dao.AuthorDao;
import pl.coderslab.SpringHibernateModul6.entity.Author;

@Controller
@RequestMapping("/author/form")
public class AuthorFormController {

    private final AuthorDao authorDao;

    public AuthorFormController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("authors", authorDao.findAll());
        return "/author/authorListing";
    }

    @GetMapping("/add")
    public String prepareAdd(Model model) {
        model.addAttribute("author", new Author());
        return "/author/authorForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("author") Author author) {
        authorDao.persist(author);
        return "redirect:/author/form/all";
    }

    @GetMapping("/edit")
    public String prepareEdit(@RequestParam int idToEdit, Model model) {
        model.addAttribute("author", authorDao.findById(idToEdit));
        return "/author/authorForm";
    }

    @PostMapping("/edit")
    public String merge(@ModelAttribute("author") Author author) {
        authorDao.merge(author);
        return "redirect:/author/form/all";
    }

    @GetMapping("/remove")
    public String prepareRemove(@RequestParam int toRemoveId, Model model) {
        model.addAttribute("author", authorDao.findById(toRemoveId));
        return "/author/remove";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam String confirmed, @RequestParam int toRemoveId) {
        if ("yes".equals(confirmed)) {
            authorDao.remove(toRemoveId);
        }
        return "redirect:/author/form/all";
    }

}
