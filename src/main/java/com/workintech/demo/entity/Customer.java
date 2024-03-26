package com.workintech.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer", schema = "s18g4")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private double salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
        account.setCustomer(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setCustomer(null);
    }

    // You can also add a method to update an account if necessary
    public void updateAccount(Account updatedAccount) {
        for (int i = 0; i < accounts.size(); i++) {
            Account currentAccount = accounts.get(i);
            if (currentAccount.getId() == updatedAccount.getId()) {
                accounts.set(i, updatedAccount);
                updatedAccount.setCustomer(this);
                return;
            }
        }
        throw new RuntimeException("Account not found and cannot be updated");
    }
}
