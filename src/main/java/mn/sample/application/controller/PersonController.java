package mn.sample.application.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import mn.sample.application.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    List<Person> persons = new ArrayList<>();

    @Get()
    public String welcome(){
        return "Welcome";
    }

    @Post("/persons")
    public Person add(Person person) {
        LOGGER.info("Adding a person");
        person.setId(persons.size() + 1);
        persons.add(person);
        return person;
    }

    @Get("/persons/{id:4}")
    public Optional<Person> findById(@NotNull Integer id) {
        LOGGER.info("Finding a person {}", id);
        return persons.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    @Get("/persons/all")
    public List<Person> findAll() {
        LOGGER.info("Returning all persons");
        return persons;
    }

   @Get("/persons/ages")
    public List<Integer> findAllAges() {
       LOGGER.info("Returning all ages");
       return persons.stream().map(Person::getAge).collect(Collectors.toList());
   }

    @Get("/persons/names")
    public List<String> findAllNames() {
        LOGGER.info("Returning all names");
        return persons.stream().map(Person::getFirstName).collect(Collectors.toList());
    }
}
