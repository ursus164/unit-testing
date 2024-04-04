package org.ursus.order;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ursus.Meal;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;

    @BeforeEach
    void initializeOrder() {
        // method that will be executed before each test
    }

    @AfterEach
    void cleanUp() {
        // method that will be executed after each test
    }

    @Test
    void testAssertArrayEquals() {
        // given
        int[] ints1 = {1, 2, 3};
        int[] ints2 = {1, 2, 3};

        // then
        assertArrayEquals(ints1, ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder() {
        //(...)
        assertThat(order.getMeals(), is(empty()));
        MatcherAssert.assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void mealListShouldNotBeEmptyAfterAddingMealToOrder() {
        // given
//        Order order = new Order();
        Meal meal = new Meal(15, "Burger");

        // when
        order.addMealToOrder(meal);

        // then
        assertThat(order.getMeals(), not(empty()));
        assertThat(order.getMeals().size(), is(equalTo(1)));

    }

    @Test
    void addMealToOrderShouldIncreaseOrderSize() {
        // given
//        Order order = new Order();
        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(27, "Sandwich");

        // when
        order.addMealToOrder(meal);

        // then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
//        assertThat(order.getMeals().get(0).getPrice(), equalTo(15));
    }

    @Test
    void removeMealFromOrderShouldDecreaseOrderSize() {
        // given
//        Order order = new Order();
        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(27, "Sandwich");

        // when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);
        order.removeMealFromOrder(meal);

        // then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal2));
        assertThat(order.getMeals(), not(contains(meal)));

    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder() {
        // given
//        Order order = new Order();
        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(27, "Sandwich");

        // when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);

        // then
        assertThat(order.getMeals(), contains(meal, meal2));
    }

    @Test
    void testIfTwoMealListsAreTheSame() {
        // given
//        Order order1 = new Order();
//        Order order2 = new Order();

        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(27, "Sandwich");
        Meal meal3 = new Meal(17, "Kebab");

        // when
        List<Meal> meals1 = Arrays.asList(meal,meal2);
        List<Meal> meals2 = Arrays.asList(meal,meal2);

        // then
        assertThat(meals1,is(meals2));
    }

    @Test
    void orderTotalPriceShouldNotExceedMaxIntValue() {
        // given
        Meal meal = new Meal(Integer.MAX_VALUE, "Burger");
        Meal meal2 = new Meal(Integer.MAX_VALUE, "Sandwich");

        // when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);

        // then
        assertThrows(IllegalStateException.class, () -> order.totalPrice());
    }

    @Test
    void emptyOrderTotalPriceShouldEqualZero() {
        // given
        // Order order = new Order(); - created in @BeforeEach

        // then
        assertThat(order.totalPrice(), is(equalTo(0)));
    }

    @Test
    void cancelingOrderShouldRemoveAllItemsFromMealsList() {
        // given
        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(27, "Sandwich");

        // when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);
        order.cancel();

        // then
        assertThat(order.getMeals(), empty());
    }

}