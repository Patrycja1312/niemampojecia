package com.example.demo.domain.rest;

public sealed interface RestFindAddressResponse {
    record Found(String name, String city) implements RestFindAddressResponse {}
    record NotFound() implements RestFindAddressResponse {}
}
