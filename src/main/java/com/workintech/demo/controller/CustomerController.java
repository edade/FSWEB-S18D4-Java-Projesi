package com.workintech.demo.controller;

import com.workintech.demo.dto.CustomerResp;
import com.workintech.demo.entity.Customer;
import com.workintech.demo.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private  final CustomerService customerService;

    @PostMapping
    public CustomerResp save(@RequestBody Customer c){
        Customer saved = this.customerService.save(c);
        return new CustomerResp(saved.getId(), saved.getEmail(), saved.getSalary());

    }

    @GetMapping
    public List<CustomerResp> getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        return customers.stream()
                .map(customer -> new CustomerResp(customer.getId(), customer.getEmail(), customer.getSalary()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerResp getCustomerById(@PathVariable long id) {
        Customer customer = customerService.find(id);
        return new CustomerResp(customer.getId(), customer.getEmail(), customer.getSalary());
    }




    @PutMapping("/{id}")
    public CustomerResp updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        customer.setId(id); // Set the ID for update
        Customer updatedCustomer = customerService.save(customer);
        return new CustomerResp(updatedCustomer.getId(), updatedCustomer.getEmail(), updatedCustomer.getSalary());
    }

    @DeleteMapping("/{id}")
    public CustomerResp deleteCustomer(@PathVariable long id) {
        Customer deletedCustomer = customerService.delete(id);
        return new CustomerResp(deletedCustomer.getId(), deletedCustomer.getEmail(), deletedCustomer.getSalary());
    }
}
