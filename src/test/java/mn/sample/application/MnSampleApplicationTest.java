package mn.sample.application;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
public class MnSampleApplicationTest {

    @Inject
    EmbeddedApplication application;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testItWorks() {
        assertTrue(application.isRunning());
    }

    @Test
    void testHealthRespondsOK() {
        Map m = client.toBlocking().retrieve(HttpRequest.GET("/health"), Map.class);

        assertNotNull(m);
        assertTrue(m.containsKey("status"));
        assertEquals("UP",m.get("status"));
    }
}