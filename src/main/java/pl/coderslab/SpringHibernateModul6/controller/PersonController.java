package pl.coderslab.SpringHibernateModul6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateModul6.dao.PersonDao;
import pl.coderslab.SpringHibernateModul6.entity.Person;
import pl.coderslab.SpringHibernateModul6.entity.PersonDetails;


/**
 *
 * Utwórz akcję wyświetlającą formularz w kontrolerze PersonController.
 * Dodaj widok formularza zawierający pola login oraz password, email.
 * Wykorzystaj w tym celu encję Person z poprzednich zajęć.
 * Dodaj akcję przetwarzająca formularz (akcja ma zakończyć się zapisem danych do bazy)- pobieraj dane za pomocą @RequestParam.
 *
 * */

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("person", new Person());
        return "person/personForm";
    }

    @PostMapping("/form")
    @ResponseBody
    public String saveForm(@ModelAttribute("person") Person testPerson) {
        personDao.persist(testPerson);
        return "Udalo sie zapisac osobe";
    }

    // Metody poniżej do obsługi przez @RequestParam
/*    @GetMapping("/form")
    public String showForm() {
        return "person/personForm";
    }

    @PostMapping("/form")
    @ResponseBody
    public String saveForm(@RequestParam String login, @RequestParam String password,
                           @RequestParam String email) {
        Person person = new Person();
        person.setLogin(login);
        person.setEmail(email);
        person.setPassword(password);
        personDao.persist(person);
        return "Udalo sie zapisac osobe";
    }*/

    @RequestMapping("/save")
    @ResponseBody
    public String persist() {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("Jan");
        personDetails.setLastName("Kowalski");
        personDetails.setStreetNumber("45A");
        personDetails.setStreet("Głowna");
        personDetails.setCity("Warszawa");

        Person person = new Person();
        person.setLogin("test123");
        person.setEmail("test123@o2.pl");
        person.setPassword("test123");
        person.setPersonDetails(personDetails);

        personDao.persist(person);
        return "Jest sukces!";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public String findById(@PathVariable("id") long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @GetMapping("/update/{id}")
    @ResponseBody
    public String merge(@PathVariable("id") long id) {
        Person person = personDao.findById(id);
        person.setPassword("Super ekstra tajne nowe haslo");
        person.getPersonDetails().setFirstName("Staszek");
        personDao.merge(person);
        return "Zaktualizowano osobe o id " + id;
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public String remove(@PathVariable("id") long id) {
        Person person = personDao.findById(id);
        personDao.remove(person);
        return "Usunieto osobe";
    }

}
