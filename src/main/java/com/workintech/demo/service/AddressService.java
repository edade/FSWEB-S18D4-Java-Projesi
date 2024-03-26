package com.workintech.demo.service;

import com.workintech.demo.entity.Account;
import com.workintech.demo.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    Address save(Address address);

    Address find(long id);

    Address delete(long id);
}
