package com.elmojke.issuetracker;

import com.elmojke.issuetracker.developer.Developer;
import com.elmojke.issuetracker.requests.DeveloperRegistrationRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
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

    @BeforeEach
    public void setUp(){
        baseUrl = baseUrl.concat(":").concat(port+"").concat("/api/v1").concat("/developers").concat("/register-developer");
    }

    @Test
    public void testRegisterDeveloper(){
        DeveloperRegistrationRequest developer = new DeveloperRegistrationRequest("Young Thug");
        Developer response = restTemplate.postForObject(baseUrl, developer, Developer.class);
        List<String> actualName = h2Repository.findById(1).stream().map(Developer::getName).collect(Collectors.toList());
        assertEquals("Young Thug", actualName.get(0));
        assertEquals(1, h2Repository.findAll().size());
    }

}
