package com.workintech.demo.controller;

import com.workintech.demo.dto.AddressResp;
import com.workintech.demo.entity.Address;
import com.workintech.demo.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;


    @GetMapping
    public List<AddressResp> getAllAddresses() {
        List<Address> addresses = addressService.findAll();
        return addresses.stream()
                .map(address -> new AddressResp(address.getId(), address.getStreet(), address.getCity()))
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public AddressResp getAddressById(@PathVariable long id) {
        Address address = addressService.find(id);
        return new AddressResp(address.getId(), address.getStreet(), address.getCity());
    }


    @PostMapping
    public AddressResp addAddress(@RequestBody Address address) {
        Address savedAddress = addressService.save(address);
        return new AddressResp(savedAddress.getId(), savedAddress.getStreet(), savedAddress.getCity());
    }


    @PutMapping("/{id}")
    public AddressResp updateAddress(@PathVariable long id, @RequestBody Address address) {
        address.setId(id); // Set the ID for update
        Address updatedAddress = addressService.save(address);
        return new AddressResp(updatedAddress.getId(), updatedAddress.getStreet(), updatedAddress.getCity());
    }


    @DeleteMapping("/{id}")
    public AddressResp deleteAddress(@PathVariable long id) {
        Address deletedAddress = addressService.delete(id);
        return new AddressResp(deletedAddress.getId(), deletedAddress.getStreet(), deletedAddress.getCity());
    }


}
