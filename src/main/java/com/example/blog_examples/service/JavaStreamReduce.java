package com.example.blog_examples.service;

import com.example.blog_examples.domain.Contact;
import com.example.blog_examples.exception.DuplicateContactException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import static com.example.blog_examples.StreamUtility.toSingleElementOrElseThrowException;

@Slf4j
public class JavaStreamReduce {

    public int sumContactsAgeUsingReduce(List<Contact> contacts) {
        return contacts.stream()
                .map(contact -> Period.between(contact.getBirthDate(), LocalDate.now()).getYears())
                .peek(age -> log.info("Contact age is: {}", age))
                .reduce(0, Integer::sum);
    }

    public int sumContactsAgeUsingSum(List<Contact> contacts) {
        return contacts.stream()
                .mapToInt(contact -> Period.between(contact.getBirthDate(), LocalDate.now()).getYears())
                .peek(age -> log.info("Contact age is: {}", age))
                .sum();
    }

    public Optional<Contact> findContactByEmailLoop(String email, List<Contact> contacts) {
        boolean contactFound = false;
        Contact contactByEmail = null;

        for (Contact contact : contacts)
            if (contact.getEmail().equals(email))
                if (!contactFound) {
                    contactFound = true;
                    contactByEmail = contact;
                } else {
                    throw new DuplicateContactException();
                }

        return contactFound ? Optional.of(contactByEmail) : Optional.empty();
    }

    public Optional<Contact> findContactByEmailReduce(String email, List<Contact> contacts) {
        return contacts.stream()
                .filter(contact -> contact.getEmail().equals(email))
                .reduce((element, otherElement) -> {
                    throw new DuplicateContactException();
                });
    }

    public Optional<Contact> findContactByEmail(String email, List<Contact> contacts) {
        return contacts.stream()
                .filter(contact -> contact.getEmail().equals(email))
                .reduce(toSingleElementOrElseThrowException(DuplicateContactException::new));
    }
}
