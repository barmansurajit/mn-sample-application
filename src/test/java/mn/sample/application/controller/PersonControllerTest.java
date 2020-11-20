package mn.sample.application.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import mn.sample.application.model.Gender;
import mn.sample.application.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URL;

@MicronautTest
class PersonControllerTest {

    @Inject
    EmbeddedServer server;

    @Test
    public void testAdd() throws MalformedURLException {
        // arrange
        HttpClient client = HttpClient.create(new URL("http://" + server.getHost() + ":" + server.getPort()));
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Smith");
        person.setAge(33);
        person.setGender(Gender.MALE);

        // act
        person = client.toBlocking().retrieve(HttpRequest.POST("/persons", person), Person.class);

        // assert
        Assertions.assertNotNull(person);
        Assertions.assertEquals(Integer.valueOf(1), person.getId());
    }

    @Test
    void testFindAll() throws MalformedURLException{

        // arrange
        HttpClient client = HttpClient.create(new URL("http://" + server.getHost() + ":" + server.getPort()));
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Smith");
        person.setAge(33);
        person.setGender(Gender.MALE);
        client.toBlocking().retrieve(HttpRequest.POST("/persons", person), Person.class);

        // act
        Person[] persons = client.toBlocking()
                .retrieve(HttpRequest.GET("/persons/all"),Person[].class);

        // assert
        Assertions.assertTrue(persons.length > 0);
    }

    @Test
    public void testFindById() throws MalformedURLException {
        HttpClient client = HttpClient.create(new URL("http://" + server.getHost() + ":" + server.getPort()));
        Person person = client.toBlocking().retrieve(HttpRequest.GET("/persons/1"), Person.class);
        Assertions.assertNotNull(person);
    }
}