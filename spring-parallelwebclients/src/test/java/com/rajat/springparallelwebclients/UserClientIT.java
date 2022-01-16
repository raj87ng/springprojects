package com.rajat.springparallelwebclients;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.rajat.springparallelwebclients.model.User;
import com.rajat.springparallelwebclients.service.UserClient;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

//@SpringBootTest
//@DirtiesContext
public class UserClientIT {

    //@Autowired
    private UserClient userClient;

    //@Autowired
    private WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        userClient = new UserClient("http://localhost:" + wireMockServer.port());
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    //FetchUser and Other User
    @Test
    public void fetchUserAndOtherUser(){
        stubFor(get(urlEqualTo("/user/" + 1)).willReturn(aResponse().withFixedDelay(10)
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(String.format("{ \"id\": %d }", 1))));
        stubFor(get(urlEqualTo("/otheruser/" + 1)).willReturn(aResponse().withFixedDelay(10)
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(String.format("{ \"id\": %d }", 2))));
        long start = System.currentTimeMillis();
        Flux<User> users = userClient.fetchUserAndOtherUser(1);
        long totalExecutionTime = System.currentTimeMillis() - start;
        System.out.println(" total execution time is : "+totalExecutionTime);
        List<User> listUsers = users.collectList().block();
        System.out.println("Users "+listUsers);
       // assertEquals("Unexpected number of users", requestsNumber, users.size());
    }

  
    @Test
    public void givenClient_whenFetchingUsers_thenExecutionTimeIsLessThanDouble() {
        // Arrange
        int requestsNumber = 5;
        int singleRequestTime = 1000;

        for (int i = 1; i <= requestsNumber; i++) {
            stubFor(get(urlEqualTo("/user/" + i)).willReturn(aResponse().withFixedDelay(singleRequestTime)
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(String.format("{ \"id\": %d }", i))));
        }

        List<Integer> userIds = IntStream.rangeClosed(1, requestsNumber)
                .boxed()
                .collect(Collectors.toList());

        // Act
        long start = System.currentTimeMillis();
        List<User> users = userClient.fetchUsers(userIds)
                .collectList()
                .block();
        long end = System.currentTimeMillis();

        // Assert
        long totalExecutionTime = end - start;
        System.out.println(" total execution time is : "+totalExecutionTime);
        assertEquals("Unexpected number of users", requestsNumber, users.size());
        assertTrue("Execution time is too big", 2 * singleRequestTime > totalExecutionTime);
    }
}
