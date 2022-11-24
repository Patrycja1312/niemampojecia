package com.example.demo.rest;

import com.example.demo.controllers.AddressController;
import com.example.demo.domain.rest.RestAddAddressRequest;
import com.example.demo.domain.rest.RestAddAddressResponse;
import com.example.demo.domain.rest.RestFindAddressRequest;
import com.example.demo.domain.rest.RestFindAddressResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAddressController {
    private final AddressController addressController;

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("hello2")
    public String hello2() {
        return "more hello";
    }

    public RestAddressController(AddressController addressController) {
        this.addressController = addressController;
    }
    @PostMapping(path = "add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestAddAddressResponse> add(@RequestBody RestAddAddressRequest restAddAddressRequest) {
        final var ok = addressController.add(restAddAddressRequest.name(), restAddAddressRequest.city());

        if (ok) {
            return ResponseEntity.ok().body(new RestAddAddressResponse.Ok("Address added"));
        } else {
            return ResponseEntity.badRequest().body(new RestAddAddressResponse.Error("something bad happened"));
        }
    }

    @PostMapping(path = "find", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestFindAddressResponse> find(@RequestBody RestFindAddressRequest request) {
        final var optionalAddress = addressController.find(request.name());

        return optionalAddress
                .<ResponseEntity<RestFindAddressResponse>>map(address -> ResponseEntity.ok().body(new RestFindAddressResponse.Found(address.getName(), address.getCity())))
                .orElseGet(() -> ResponseEntity.badRequest().body(new RestFindAddressResponse.NotFound()));
    }
}
