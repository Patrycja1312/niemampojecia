package com.example.demo;

import com.example.demo.domain.rest.RestAddAddressRequest;
import com.example.demo.domain.rest.RestAddAddressResponse;
import com.example.demo.domain.rest.RestFindAddressRequest;
import com.example.demo.domain.rest.RestFindAddressResponse;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.rest.RestAddressController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class RestAddressControllerTest {

    @Autowired
    RestAddressController restAddressController;

    @Autowired
    AddressRepository addressRepository;

    @BeforeEach
    public void prepare() {
        addressRepository.deleteAll();
    }

    @Test
    public void addTest() {
        final var result = restAddressController.add(new RestAddAddressRequest("name0", "Big City"));
        Assertions.assertInstanceOf(RestAddAddressResponse.Ok.class, result.getBody());
    }

    @Test
    public void addTest2() {
        final var result = restAddressController.add(new RestAddAddressRequest("name0", "Big City"));
        Assertions.assertInstanceOf(RestAddAddressResponse.Ok.class, result.getBody());
        final var result2 = restAddressController.add(new RestAddAddressRequest("name0", "Big City"));
        Assertions.assertInstanceOf(RestAddAddressResponse.Error.class, result2.getBody());
    }


    @Test
    public void findTest() {
        final var result = restAddressController.add(new RestAddAddressRequest("name0", "Big City"));
        Assertions.assertInstanceOf(RestAddAddressResponse.Ok.class, result.getBody());

        final var exists =restAddressController.find(new RestFindAddressRequest("name0"));
        Assertions.assertInstanceOf(RestFindAddressResponse.Found.class, exists.getBody());
    }
}