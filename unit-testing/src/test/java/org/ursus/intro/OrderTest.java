package org.ursus.intro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        order = new Order();
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
        // given
//        Order order = new Order();

        // then
        assertThat(order.getMeals(), is(empty()));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
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
}