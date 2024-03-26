package com.workintech.demo.service;

import com.workintech.demo.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer save(Customer customer);

    Customer find(long id);
    Customer delete(long id);

}
