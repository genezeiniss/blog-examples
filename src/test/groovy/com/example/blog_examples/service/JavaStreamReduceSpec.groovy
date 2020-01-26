package com.example.gene_test.service

import com.example.gene_test.domain.Contact
import com.example.gene_test.exception.DuplicateContactException
import com.github.javafaker.Faker
import spock.lang.Specification

import java.time.LocalDate

class JavaStreamReduceSpec extends Specification {

    Faker faker = new Faker()
    JavaStreamReduce javaStreamReduce = new JavaStreamReduce()

    def "sum contact age using reduce"() {
        expect: "method expected to return sum"
        assert javaStreamReduce.sumContactsAgeUsingReduce(contactsStream()) == 152
    }

    def "sum contact age using sum"() {
        expect: "method expected to return sum"
        assert javaStreamReduce.sumContactsAgeUsingSum(contactsStream()) == 152
    }

    def "find contact by email - positive flow"() {
        when: "find contact by email method is called"
        javaStreamReduce.findContactByEmail("reduce.single@mailinator.com", contactsStream())

        then: "no exception expected to be thrown"
        noExceptionThrown()
    }

    def "find contact by email - duplicate contact"() {
        when: "find contact by email method is called"
        javaStreamReduce.findContactByEmail("reduce.duplicate@mailinator.com", contactsStream())

        then: "duplicate contact expected to be thrown"
        thrown(DuplicateContactException)
    }

    private List<Contact> contactsStream() {
        return Arrays.asList(
                Contact.builder()
                        .email(faker.internet().emailAddress())
                        .birthDate(LocalDate.now().minusYears(30))
                        .build(),
                Contact.builder()
                        .email("reduce.duplicate@mailinator.com")
                        .birthDate(LocalDate.now().minusYears(40))
                        .build(),
                Contact.builder()
                        .email("reduce.single@mailinator.com")
                        .birthDate(LocalDate.now().minusYears(35))
                        .build(),
                Contact.builder()
                        .email(faker.internet().emailAddress())
                        .birthDate(LocalDate.now().minusYears(20))
                        .build(),
                Contact.builder()
                        .email("reduce.duplicate@mailinator.com")
                        .birthDate(LocalDate.now().minusYears(27))
                        .build());
    }
}
