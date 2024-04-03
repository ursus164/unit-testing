package org.ursus.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Unit {
    private static Random random = new Random();
    private Coordinates coordinates;
    private List<Cargo> cargo;
    private int fuel;
    private int maxFuel;

    public int getFuel() {
        return fuel;
    }

    public int getCurrentCargoWeight() {
        return currentCargoWeight;
    }

    private int maxCargoWeight;
    private int currentCargoWeight;

    public Unit(Coordinates coordinates, int maxFuel, int maxCargoWeight) {
        this.coordinates = coordinates;
        this.maxFuel = maxFuel;
        this.fuel = maxFuel;
        this.maxCargoWeight = maxCargoWeight;
        this.currentCargoWeight = 0;
        this.cargo = new ArrayList<>();
    }

    public List<Cargo> getCargo() {
        return cargo;
    }

    Coordinates move(int x, int y) {
        if(this.fuel - (x + y) < 0) {
            throw new IllegalArgumentException("Not enough fuel");
        }

        Coordinates newCoordinates = Coordinates.copy(this.coordinates, x, y);
        this.coordinates = newCoordinates;
        this.fuel = this.fuel- (x + y);

        return newCoordinates;
    }

    public void tankUp() {
        int restPoints = random.nextInt(10);

        if(restPoints + this.fuel > this.maxFuel) {
            this.fuel = this.maxFuel;
        } else {
            this.fuel += restPoints;
        }
    }

    public void loadCargo(Cargo cargo) {
        if(this.currentCargoWeight + cargo.getWeight() > this.maxCargoWeight) {
            throw new IllegalArgumentException("Not enough space");
        }

        this.cargo.add(cargo);
        this.currentCargoWeight += cargo.getWeight();
    }

    public void unloadCargo(Cargo cargo) {
        if(!this.cargo.contains(cargo)) {
            throw new IllegalArgumentException("Cargo not found");
        }

        this.cargo.remove(cargo);
        this.currentCargoWeight = calculateCurrentCargoWeight();
    }

    public void unloadAllCargo() {
        this.cargo.clear();
        this.currentCargoWeight = 0;
    }

    private int calculateCurrentCargoWeight() {
        return this.cargo.stream()
                .mapToInt(Cargo::getWeight)
                .sum();
    }

    public int getWeight() {
        return this.currentCargoWeight;
    }
}
