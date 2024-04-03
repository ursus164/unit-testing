package org.ursus.exercise;

import java.util.Objects;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        if(x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative");
        }
        if(x > 100 || y > 100) {
            throw new IllegalArgumentException("Coordinates cannot be higher than 100");
        }
        this.x = x;
        this.y = y;
    }

    public static Coordinates copy(Coordinates coordinates, int x, int y) {
        return new Coordinates(coordinates.x + x, coordinates.y + y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
