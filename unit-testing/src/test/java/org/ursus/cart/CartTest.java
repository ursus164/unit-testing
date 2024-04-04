package org.ursus.cart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ursus.cart.Cart;
import org.ursus.order.Order;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test cases for Cart")
class CartTest {

    @Test
    void simulateLargeOrder() {
        // given
        Cart cart = new Cart();

        // then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrder);
    }

    @Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart() {
        // given
        Cart cart = new Cart();
        Order order = new Order();

        // when
        cart.addOrderToCart(order);

        // then
        assertThat(cart.getOrders(), anyOf(
                notNullValue(),
                hasSize(1),
                is(not(emptyCollectionOf(Order.class))),
                is(not(empty()))

        ));

        assertThat(cart.getOrders(), allOf(
                notNullValue(),
                hasSize(1),
                is(not(emptyCollectionOf(Order.class))),
                is(not(empty()))

        ));

        assertAll(
                // all assertions should pass
                () -> assertThat(cart.getOrders(), notNullValue()),
                () -> assertThat(cart.getOrders(), hasSize(1)),
                () -> assertThat(cart.getOrders(), is(not(emptyCollectionOf(Order.class)))),
                () -> assertThat(cart.getOrders(), is(not(empty()))),
                () -> assertThat(cart.getOrders().get(0).getMeals(), empty())
        );
    }
}