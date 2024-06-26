package org.ursus.cart;

import org.ursus.Meal;
import org.ursus.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Order> orders = new ArrayList<>();

    void addOrderToCart(Order order) {
        orders.add(order);
    }
    void clearCart() {
        orders.clear();
    }

    void simulateLargeOrder() {
        for (int i = 0; i < 1_000; i++) {
            Meal meal = new Meal(i%10 + 10, "Burger no " + i);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCart(order);
        }
        System.out.println("Cart size: " + orders.size());
        clearCart();
    }

    public List<Order> getOrders() {
        return orders;
    }
}
