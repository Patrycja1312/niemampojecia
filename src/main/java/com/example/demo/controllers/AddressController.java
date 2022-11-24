package com.example.demo.controllers;

import com.example.demo.repositories.AddressRepository;
import com.example.demo.domain.database.Address;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AddressController {

    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public boolean add(String name, String city) {
        if (addressRepository.existsById(name)) {return false;}
        else {
            addressRepository.save(new Address(name, city));
            return true;
        }
    }

    public Optional<Address> find(String name) {
        return addressRepository.findById(name);
    }
}
