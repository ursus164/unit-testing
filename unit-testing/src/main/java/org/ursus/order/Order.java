package org.ursus.order;

import org.ursus.Meal;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private OrderStatus orderStatus;


    private List<Meal> meals = new ArrayList<>();

    public void addMealToOrder(Meal meal) {
        meals.add(meal);
    }

    public void removeMealFromOrder(Meal meal) {
        meals.remove(meal);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void cancel() {
        meals.clear();
    }

    public int totalPrice() {
        int sum = this.meals.stream().mapToInt(Meal::getPrice).sum();

        if (sum < 0) {
            throw new IllegalStateException("Price limit exceeded");
        } else {
            return sum;
        }
    }
}
