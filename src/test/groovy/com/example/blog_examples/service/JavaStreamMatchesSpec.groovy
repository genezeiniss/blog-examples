package com.example.gene_test.service

import com.example.gene_test.domain.Contact
import com.github.javafaker.Faker
import spock.lang.Specification

import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.stream.Stream

class JavaStreamMatchesSpec extends Specification {

    Faker faker = new Faker()
    JavaStreamMatches javaStreamMatches = new JavaStreamMatches()

    def "any match"() {
        expect: "method expected to return true"
        assert javaStreamMatches.isAnyContactConfirmedEmail(contactsStream())
    }

    def "any match - empty stream"() {
        expect: "method expected to return false"
        assert !javaStreamMatches.isAnyContactConfirmedEmail(Stream.empty())
    }

    def "all match"() {
        expect: "method expected to return false"
        assert !javaStreamMatches.isAllContactConfirmedEmail(contactsStream())
    }

    def "all match - empty stream"() {
        expect: "method expected to return true"
        assert javaStreamMatches.isAllContactConfirmedEmail(Stream.empty())
    }

    def "none match"() {
        expect: "method expected to return false"
        assert !javaStreamMatches.isNoneContactConfirmedEmail(contactsStream())
    }

    def "none match - empty stream"() {
        expect: "method expected to return true"
        assert javaStreamMatches.isNoneContactConfirmedEmail(Stream.empty())
    }

    private Stream<Contact> contactsStream() {
        return Stream.of(
                Contact.builder().id(1).email(faker.internet().emailAddress()).build(),
                Contact.builder()
                        .id(2)
                        .email(faker.internet().emailAddress())
                        .emailConfirmedOn(Instant.now().minus(faker.random().nextInt(1, 10), ChronoUnit.DAYS)).build(),
                Contact.builder().id(3).email(faker.internet().emailAddress()).build(),
                Contact.builder().id(4).email(faker.internet().emailAddress()).build())
    }
}
