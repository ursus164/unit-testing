package org.ursus.intro;

import java.util.Objects;

public class Meal {


    public int getQuantity() {
        return quantity;
    }

    private int quantity;
    private int price;

    public Meal(int quantity, int price, String name) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name;

    public Meal(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public Meal(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

    public int getDiscountedPrice(int discount) {
        if(discount > price) {
            throw new IllegalArgumentException("Discount cannot be higher than the price!");
        }
        return price - discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return price == meal.price && Objects.equals(name, meal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
