package org.raitega.sample.taskmanager;

import org.junit.jupiter.api.Test;
import org.raitega.sample.taskmanager.dto.TaskItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import static org.junit.Assert.assertFalse;

/**
 * Test class for the application
 * @author      Farzan Zubair
 */


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RandomPortWebTestClientExampleTests {

    @Test
    void testServerItems(@Autowired TestRestTemplate restTemplate) {
        assertFalse(restTemplate.exchange("/task/all", HttpMethod.GET, new HttpEntity(new HttpHeaders()), TaskItems.class,
                java.util.Optional.ofNullable(null)).getBody().getTaskViewItems().isEmpty());

    }

}