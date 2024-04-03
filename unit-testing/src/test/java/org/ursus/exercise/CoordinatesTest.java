package org.ursus.exercise;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void testIfNewCoordinatesAreCorrect() {
        Coordinates coordinates = new Coordinates(1,1);
        Coordinates newCoordinates = Coordinates.copy(coordinates,1,3);

        assertThat(coordinates.getX(), equalTo(1));
        assertThat(coordinates.getY(), equalTo(1));
        assertThat(newCoordinates.getX(), equalTo(2));
        assertThat(newCoordinates.getY(), equalTo(4));
    }

    @Test
    void testIfCoordinatesAreEqual() {
        Coordinates coordinates = new Coordinates(1,1);
        Coordinates newCoordinates = Coordinates.copy(coordinates,1,3);

        Coordinates coordinates1 = new Coordinates(2,4);

        assertThat(newCoordinates,  is(equalTo(coordinates1)));
    }

    @Test
    void testIfCoordinatesAreTheSameAfterCopyingWithZeroValues() {
        Coordinates coordinates = new Coordinates(1,1);
        Coordinates newCoordinates = Coordinates.copy(coordinates,0,0);

        assertThat(coordinates,  is(equalTo(newCoordinates)));
    }

    @Test
    void executionShouldBeThrownWhenCoordinatesAreNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(1, -1));
    }

    @Test
    void executionShouldBeThrownWhenCoordinatesAreHigherThan100() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(101, 1));
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(1, 101));
    }
}