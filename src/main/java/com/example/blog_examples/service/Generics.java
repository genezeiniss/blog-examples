package com.example.blog_examples.service;

import com.example.blog_examples.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class Generics {

    public void generalListExample() {

        List<Customer> contacts = new ArrayList<>();
//        contacts.add(Contact.builder().email("contact@email.com").build());
        contacts.add(Customer.builder().businessName("Beverages Ltd.").build());

        Customer contact = contacts.get(0);
    }
}