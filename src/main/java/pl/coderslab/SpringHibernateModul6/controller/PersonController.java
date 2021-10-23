package pl.coderslab.SpringHibernateModul6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateModul6.dao.PersonDao;
import pl.coderslab.SpringHibernateModul6.entity.Person;
import pl.coderslab.SpringHibernateModul6.entity.PersonDetails;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

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
