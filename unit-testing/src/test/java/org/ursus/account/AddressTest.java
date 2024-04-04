package org.ursus.account;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @ParameterizedTest
    @CsvSource({"Fabryczna, 10, 30-301, Kraków", "Armii Krajowej, 57, 30-150, Kraków"})
    void givenAddressesShouldNotBeEmpty(String street, String number, String postalCode, String city) {
        assertThat(street, notNullValue());
        assertThat(number, notNullValue());
        assertThat(postalCode, notNullValue());
        assertThat(postalCode, containsString("-"));
        assertThat(city, notNullValue());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/address.csv")
    void givenAddressesShouldNotBeEmptyFromCsv(String street, String number, String postalCode, String city) {
        assertThat(street, notNullValue());
        assertThat(number, notNullValue());
        assertThat(postalCode, notNullValue());
        assertThat(postalCode, containsString("-"));
        assertThat(city, notNullValue());
    }

}

