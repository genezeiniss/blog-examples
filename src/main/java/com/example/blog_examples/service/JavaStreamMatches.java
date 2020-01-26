package com.example.blog_examples.service;

import com.example.blog_examples.domain.Contact;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;
import java.util.stream.Stream;

@Slf4j
public class JavaStreamMatches {

    Predicate<Contact> emailConfirmationPredicate() {
        return contact -> contact.getEmailConfirmedOn() != null;
    }

    public boolean isAnyContactConfirmedEmail(Stream<Contact> contacts) {
        return contacts
                .anyMatch(emailConfirmationPredicate());
    }

    public boolean isAllContactConfirmedEmail(Stream<Contact> contacts) {
        return contacts
                .peek(contact -> log.info("Contact id: {}", contact.getId()))
                .allMatch(emailConfirmationPredicate());
    }

    public boolean isNoneContactConfirmedEmail(Stream<Contact> contacts) {
        return contacts
                .peek(contact -> log.info("Contact id: {}", contact.getId()))
                .noneMatch(emailConfirmationPredicate());
    }
}