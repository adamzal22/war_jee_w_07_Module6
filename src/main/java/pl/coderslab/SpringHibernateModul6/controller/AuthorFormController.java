package pl.coderslab.SpringHibernateModul6.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateModul6.dao.AuthorDao;
import pl.coderslab.SpringHibernateModul6.entity.Author;
import pl.coderslab.SpringHibernateModul6.repository.AuthorRepository;
import pl.coderslab.SpringHibernateModul6.search.AuthorSearchMode;

import javax.validation.Valid;

@Controller
@RequestMapping("/author/form")
public class AuthorFormController {

    private final AuthorDao authorDao;
    private final AuthorRepository authorRepository;

    public AuthorFormController(AuthorDao authorDao, AuthorRepository authorRepository) {
        this.authorDao = authorDao;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/all")
    public String all(Model model,
                      @RequestParam(value = "field", required = false) AuthorSearchMode field,
                      @RequestParam(value = "query", required = false) String query) {
        if (field != null && StringUtils.isNotEmpty(query)) {
            switch (field) {
                case EMAIL:
                    model.addAttribute("authors", authorRepository.findByEmail(query));
                    break;
                case PESEL:
                    model.addAttribute("authors", authorRepository.findByPesel(query));
                    break;
            }
            model.addAttribute("selectedField", field);
        } else {
            model.addAttribute("authors", authorDao.findAll());
        }
        model.addAttribute("query", query);
        model.addAttribute("searchMode", AuthorSearchMode.values());
        return "/author/authorListing";
    }

    @GetMapping("/add")
    public String prepareAdd(Model model) {
        model.addAttribute("author", new Author());
        return "/author/authorForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("author") @Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "/author/authorForm";
        }
        authorDao.persist(author);
        return "redirect:/author/form/all";
    }

    @GetMapping("/edit")
    public String prepareEdit(@RequestParam int idToEdit, Model model) {
        model.addAttribute("author", authorDao.findById(idToEdit));
        return "/author/authorForm";
    }

    @PostMapping("/edit")
    public String merge(@ModelAttribute("author") @Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "/author/authorForm";
        }
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
