package org.ursus.exercise;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void testIfLoadForNewUnitIsEqualToZero() {
        Unit unit1 = new Unit(new Coordinates(1, 1), 100, 100);

        assertThat(unit1.getCargo().size(), is(equalTo(0)));
        assertThat(unit1.getCurrentCargoWeight(), equalTo(0));
    }

    @Test
    void exceptionShouldBeThrownIfUnitHasToLowFuel() {
        Unit unit1 = new Unit(new Coordinates(1, 1), 80, 100);

        assertThrows(IllegalArgumentException.class, () -> unit1.move(100, 100));
    }

    @Test
    void testIfUnitCoordinatesAreCorrectAfterMoving() {
        Unit unit1 = new Unit(new Coordinates(1, 1), 100, 100);

        Coordinates newCoordinates = unit1.move(1, 3);

        assertThat(newCoordinates.getX(), equalTo(2));
        assertThat(newCoordinates.getY(), equalTo(4));
    }

    @Test
    void testIfFuelLevelIsCorrectAfterMoving() {
        Unit unit1 = new Unit(new Coordinates(1, 1), 100, 100);

        unit1.move(1, 3);
        unit1.move(4, 3);

        assertThat(unit1.getFuel(), equalTo(89));
    }

    @RepeatedTest(20)
    void testIfFuelLevelIsCorrectAfterTankUp() {
        Unit unit = new Unit(new Coordinates(1, 1), 100, 100);

        unit.move(1, 3);
        unit.move(4, 3);
        int fuelBefore = unit.getFuel();
        unit.tankUp();
        int fuelAfter = unit.getFuel();

        assertThat(fuelAfter, is(greaterThanOrEqualTo(fuelBefore)));
    }


    @Test
    void exceptionShouldBeThrownIfCargoWeightIsHigherThanMaxCargoWeight() {
        Unit unit1 = new Unit(new Coordinates(1, 1), 100, 100);

        Cargo cargo1 = new Cargo("Fish",101);

        assertThrows(IllegalArgumentException.class, () -> unit1.loadCargo(cargo1));
        assertThat(unit1.getWeight(), is(equalTo(0)));
    }

    @Test
    void testIfUnitWeightAfterLoadingCargoIsCorrect() {
        Unit unit1 = new Unit(new Coordinates(1, 1), 100, 100);

        Cargo cargo1 = new Cargo("Fish", 50);
        Cargo cargo2 = new Cargo("Ham", 30);

        unit1.loadCargo(cargo1);
        unit1.loadCargo(cargo2);

        assertThat(cargo1.getWeight(), is(equalTo(50)));
        assertThat(unit1.getCurrentCargoWeight(), is(equalTo(80)));
    }

    @Test
    void testIfCargoListIsCorrectAfterLoadingCargo() {
        Unit unit1 = new Unit(new Coordinates(1, 1), 100, 100);

        Cargo cargo1 = new Cargo("Fish", 50);
        Cargo cargo2 = new Cargo("Ham", 30);

        unit1.loadCargo(cargo1);
        unit1.loadCargo(cargo2);

        assertThat(unit1.getCargo().size(), is(equalTo(2)));
        assertThat(unit1.getCargo().get(0), is(equalTo(cargo1)));
        assertThat(unit1.getCargo().get(1), is(equalTo(cargo2)));
    }

    @Test
    void exceptionShouldBeThrownIfCargoIsNotOnTheList() {
        Unit unit1 = new Unit(new Coordinates(1, 1), 100, 100);

        Cargo cargo1 = new Cargo("Fish", 50);
        Cargo cargo2 = new Cargo("Ham", 30);

        unit1.loadCargo(cargo1);
        unit1.loadCargo(cargo2);

        Cargo cargo3 = new Cargo("Apple", 20);

        assertThrows(IllegalArgumentException.class, () -> unit1.unloadCargo(cargo3));
    }

    @Test
    void testIfUnitWeightAfterUnloadingCargoIsCorrect() {
        Unit unit1 = new Unit(new Coordinates(1, 1), 100, 100);

        Cargo cargo1 = new Cargo("Fish", 60);
        Cargo cargo2 = new Cargo("Ham", 30);

        unit1.loadCargo(cargo1);
        unit1.loadCargo(cargo2);

        unit1.unloadCargo(cargo1);

        assertThat(unit1.getCurrentCargoWeight(), is(equalTo(30)));
        assertThat(unit1.getCargo().get(0).getName(), is(equalTo("Ham")));
    }

    @Test
    void testIfCargoListIsCorrectAfterUnloadingCargo() {
        Unit unit1 = new Unit(new Coordinates(1, 1), 100, 100);

        Cargo cargo1 = new Cargo("Fish", 50);
        Cargo cargo2 = new Cargo("Ham", 30);

        unit1.loadCargo(cargo1);
        unit1.loadCargo(cargo2);

        unit1.unloadCargo(cargo1);

        assertThat(unit1.getCargo().size(), is(equalTo(1)));
        assertThat(unit1.getCargo().get(0), is(equalTo(cargo2)));
    }

    @Test
    void testIfUnitWeightAfterUnloadingAllCargoIsCorrect() {
        Unit unit1 = new Unit(new Coordinates(1, 1), 100, 100);

        Cargo cargo1 = new Cargo("Fish", 50);
        Cargo cargo2 = new Cargo("Ham", 30);

        unit1.loadCargo(cargo1);
        unit1.loadCargo(cargo2);

        unit1.unloadAllCargo();

        assertThat(unit1.getCurrentCargoWeight(), is(equalTo(0)));
        assertThat(unit1.getCargo().size(), is(equalTo(0)));
    }
}