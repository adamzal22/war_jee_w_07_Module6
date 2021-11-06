package pl.coderslab.SpringHibernateModul6.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateModul6.entity.Publisher;
import pl.coderslab.SpringHibernateModul6.repository.PublisherRepository;
import pl.coderslab.SpringHibernateModul6.search.PublisherSearchMode;

import javax.validation.Valid;

@Controller
@RequestMapping("/publisher/form")
public class PublisherFormController {

    private final PublisherRepository publisherRepository;

    public PublisherFormController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/all")
    public String showAll(Model model,
                          @RequestParam(value = "field", required = false) PublisherSearchMode field,
                          @RequestParam(value = "query", required = false) String query) {
        if (field != null && StringUtils.isNotEmpty(query)) {
            switch (field) {
                case NIP:
                    model.addAttribute("publishers", publisherRepository.findByNip(query));
                    break;
                case REGON:
                    model.addAttribute("publishers", publisherRepository.findByRegon(query));
                    break;
            }
            model.addAttribute("selectedField", field);
        } else {
            model.addAttribute("publishers", publisherRepository.findAll());
        }
        model.addAttribute("query", query);
        model.addAttribute("searchMode", PublisherSearchMode.values());
        return "/publisher/publisherListing";
    }

    @GetMapping("/add")
    public String preparePublisherAdd(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "/publisher/publisherForm";
    }

    @PostMapping("/add")
    public String savePublisher(@ModelAttribute("publisher") @Valid Publisher publisher, BindingResult result) {
        if(result.hasErrors()) {
            return "/publisher/publisherForm";
        }
        publisherRepository.save(publisher);
        return "redirect:/publisher/form/all";
    }

    @GetMapping("/edit")
    public String prepareEdit(@RequestParam long idToEdit, Model model) {
        model.addAttribute("publisher", publisherRepository.getById(idToEdit));
        return "/publisher/publisherForm";
    }

    @PostMapping("/edit")
    public String merge(@ModelAttribute("publisher") @Valid Publisher publisher, BindingResult result) {
        if(result.hasErrors()) {
            return "/publisher/publisherForm";
        }
        publisherRepository.save(publisher);
        return "redirect:/publisher/form/all";
    }

    @GetMapping("/remove")
    public String prepareRemove(@RequestParam long toRemoveId, Model model) {
        model.addAttribute("publisher", publisherRepository.getById(toRemoveId));
        return "/publisher/remove";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam String confirmed, @RequestParam long toRemoveId) {
        if ("yes".equals(confirmed)) {
            publisherRepository.deleteById(toRemoveId);
        }
        return "redirect:/publisher/form/all";
    }

}
