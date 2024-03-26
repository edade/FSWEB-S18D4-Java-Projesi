package com.workintech.demo.service;

import com.workintech.demo.entity.Customer;
import com.workintech.demo.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer find(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        return optionalCustomer.orElseThrow(()-> new RuntimeException("Customer not found"));
    }

    @Override
    public Customer delete(long id) {
        Customer customer = find(id);
        customerRepository.delete(customer);
        return customer;
    }
}
