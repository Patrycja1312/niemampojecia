package com.example.demo.domain.rest;

public sealed interface RestAddAddressResponse {
    record Ok(String message) implements RestAddAddressResponse{}
    record Error(String message) implements RestAddAddressResponse{}
}
