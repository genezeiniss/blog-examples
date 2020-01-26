package com.example.gene_test.domain;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;

@Value
@Builder(builderClassName = "Builder", toBuilder = true)
public class Contact {

    int id;
    String firstName;
    String lastName;
    String email;
    Instant emailConfirmedOn;
    LocalDate birthDate;
}