package mn.sample.application.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import mn.sample.application.model.Person;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller("/persons")
public class PersonController {
    List<Person> persons = new ArrayList<>();

    @Post
    public Person add(Person person) {
        person.setId(persons.size() + 1);
        persons.add(person);
        return person;
    }

    @Get("/{id:4}")
    public Optional<Person> findById(@NotNull Integer id) {
        return persons.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    @Get
    public List<Person> findAll() {
        return persons;
    }
}
