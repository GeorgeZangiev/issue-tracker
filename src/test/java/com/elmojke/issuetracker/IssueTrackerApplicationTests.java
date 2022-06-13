package com.elmojke.issuetracker;

import com.elmojke.issuetracker.developer.Developer;
import com.elmojke.issuetracker.requests.DeveloperRegistrationRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IssueTrackerApplicationTests {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private TestH2Repository h2Repository;

    @BeforeAll
    public static void init(){
        restTemplate = new RestTemplate();
    }

    @Test
    @Sql(statements = "DELETE FROM DEVELOPER WHERE name = 'Young Thug'",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testRegisterDeveloper(){
        baseUrl = baseUrl.concat(":").concat(port+"").concat("/api/v1").concat("/developers").concat("/register-developer");
        DeveloperRegistrationRequest developer = new DeveloperRegistrationRequest("Young Thug");
        Developer response = restTemplate.postForObject(baseUrl, developer, Developer.class);
        String actualName = h2Repository.findById(1).get().getName();
        assertEquals("Young Thug", actualName);
        assertEquals(1, h2Repository.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO DEVELOPER (id, name) VALUES (2, 'Lil Uzi Vert')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM DEVELOPER WHERE name = 'Lil Uzi Vert'",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetDevelopers(){
        baseUrl = baseUrl.concat(":").concat(port+"").concat("/api/v1").concat("/developers");
        List<Developer> developers = restTemplate.getForObject(baseUrl, List.class);
        assertEquals(1, h2Repository.findAll().size());
        assertEquals(1, developers.size());
    }

    @Test
    @Sql(statements = "INSERT INTO DEVELOPER (id, name) VALUES (3, 'ASAP Rocky')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM DEVELOPER WHERE name = 'A$AP Rocky'",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateDeveloper(){
        baseUrl = baseUrl.concat(":").concat(port+"").concat("/api/v1").concat("/developers/{id}");
        Developer developer = Developer.builder().id(3).name("A$AP Rocky").build();
        restTemplate.put(baseUrl, developer, 3);
        Developer developerFromDB = h2Repository.findById(3).get();
        assertAll(
                () -> assertNotNull(developerFromDB),
                () -> assertEquals(3, developerFromDB.getId()),
                () -> assertEquals("A$AP Rocky", developerFromDB.getName())
        );
    }

    @Test
    @Sql(statements = "INSERT INTO DEVELOPER (id, name) VALUES (4, 'Playboi Carti')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testDeleteDeveloper(){
        baseUrl = baseUrl.concat(":").concat(port+"").concat("/api/v1").concat("/developers/{id}");
        assertEquals(1, h2Repository.findAll().size());
        restTemplate.delete(baseUrl, 4);
        assertEquals(0, h2Repository.findAll().size());
    }
}
