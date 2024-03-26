package com.workintech.demo.service;

import com.workintech.demo.entity.Account;
import com.workintech.demo.entity.Address;
import com.workintech.demo.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService{

    private  final AddressRepository addressRepository;
    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address find(long id) {
        Optional<Address> accountOptional = addressRepository.findById(id);
        return accountOptional.orElseThrow(()-> new RuntimeException("address not found")) ;
    }

    @Override
    public Address delete(long id) {
        Address address =find(id);
        addressRepository.delete(address);
        return address;
    }
}
