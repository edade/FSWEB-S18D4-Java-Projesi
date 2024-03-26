package com.workintech.demo.controller;

import com.workintech.demo.dto.AccountResp;
import com.workintech.demo.dto.CustomerResp;
import com.workintech.demo.entity.Account;
import com.workintech.demo.entity.Customer;
import com.workintech.demo.service.AccountService;
import com.workintech.demo.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private  final CustomerService customerService;

    private final AccountService accountService;

    @GetMapping
    public List<Account> findAll(){
        return  accountService.findAll();
    }

    @PostMapping("/{customerId}")
    public AccountResp save(@RequestBody Account account, @PathVariable long customerId){
       Customer customer = customerService.find(customerId);
        customer.getAccounts().add(account);
        account.setCustomer(customer);
        Account savedAccount = accountService.save(account);
        return new AccountResp(savedAccount.getId(), savedAccount.getAccountName(),savedAccount.getMoneyAmount(), new CustomerResp(customer.getId(),customer.getEmail(),customer.getSalary()));
    }

    @PutMapping("/{customerId}")
    public AccountResp update(@RequestBody Account account, @PathVariable long customerId) {
        Customer customer = customerService.find(customerId);
        Account foundAccount = null;
        for (Account acc : customer.getAccounts()) {
            if (account.getId() == acc.getId()) {
                foundAccount = acc;
            }
        }
        if (foundAccount == null) {
            throw new RuntimeException("Account(" + account.getId() + ") not found for this customer: " + customerId);
        }

        int indexOfFound = customer.getAccounts().indexOf(foundAccount);
        customer.getAccounts().set(indexOfFound, account);
        account.setCustomer(customer);
        accountService.save(account);

        return new AccountResp(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                new CustomerResp(customer.getId(), customer.getEmail(), customer.getSalary()));
    }


    @DeleteMapping("/{id}")
    public AccountResp remove(@PathVariable long id) {
        Account account = accountService.find(id);
        if (account != null) {
            accountService.delete(id);
            return new AccountResp(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                    new CustomerResp(account.getCustomer().getId(), account.getCustomer().getEmail(), account.getCustomer().getSalary()));
        } else {
            throw new RuntimeException("no account found!");
        }

    }
}
