package com.example.demo;

import com.example.demo.domain.rest.RestAddAddressRequest;
import com.example.demo.domain.rest.RestFindAddressResponse;
import com.example.demo.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest
public class WebClientTest {

    WebTestClient client;

    @Autowired
    AddressRepository addressRepository;

    @BeforeEach
    void setUp(ApplicationContext context) {
        addressRepository.deleteAll();
        client = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void testAdd() {
        client
                .post()
                .uri("/add")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new RestAddAddressRequest("Some Name", "Big City")))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody(RestFindAddressResponse.Found.class).returnResult();
    }

    @Test
    void testFind() {
        client
                .post()
                .uri("/add")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new RestAddAddressRequest("Some Name", "Big City")))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody(RestFindAddressResponse.Found.class).returnResult();

        client
                .post()
                .uri("/find")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new RestAddAddressRequest("Some Name", "Big City")))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody(RestFindAddressResponse.Found.class).returnResult();
    }

    @Test
    public void testNotFound() {
        client
                .post()
                .uri("/find")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new RestAddAddressRequest("Some Name", "Big City")))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
                .expectBody(RestFindAddressResponse.Found.class).returnResult();
    }
}
