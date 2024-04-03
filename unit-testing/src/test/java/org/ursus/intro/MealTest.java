package org.ursus.intro;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void getDiscountedPrice() {
// given
        Meal meal = new Meal(100);

        // when
        int discountedPrice = meal.getDiscountedPrice(20);

        // then
        assertEquals(80, discountedPrice);
        assertThat(discountedPrice, equalTo(80));
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual() {
        // given
        Meal meal1 = new Meal(100);
        Meal meal2 = meal1;

        // then
        assertSame(meal1, meal2);
        assertThat(meal1, sameInstance(meal2));
    }

    @Test
    void twoSameMealsShouldBeEqual() {
        // given
        Meal meal1 = new Meal(100, "Pizza");
        Meal meal2 = new Meal(100, "Pizza");

        // then
        assertEquals(meal1, meal2, "Two same meals should be equal");
        // asercja jest fałszywa bo korzystamy z metody equals z klasy Object, która porównuje referencje
        assertThat(meal1,not(sameInstance(meal2)));
    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice() {
        // given
        Meal meal = new Meal(8, "Soup");

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(10));
        // we use lambda because we have to use a functional interface "executable", with method "execute"
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 19})
    void mealPricesShouldBeLowerThan20(int price) {
        // given
        Meal meal = new Meal(price, "Soup");

        // then
        assertThat(meal.getPrice(), lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void foodNameShouldBeEqualToValuePassedAsParameter(String name, int price) {
        // testing if the name of the meal contains the word "burger" and the price is greater than 10
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));

    }

    private static Stream<Arguments> createMealsWithNameAndPrice() {
        // helper method to create a stream of arguments
        return Stream.of(
                Arguments.of("Baconburger", 20),
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 15)
        );
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNameShouldEndWithCake(String name) {
        // testing if the name of the cake ends with "cake"
        assertThat(name, endsWith("cake"));
        assertThat(name, notNullValue());
    }

    private static Stream<String> createCakeNames() {
        // helper method to create a stream of arguments
        List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitcake", "Cupcake");
        return cakeNames.stream();
    }

    @Tag("fries")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices() {

        Order order = new Order();
        order.addMealToOrder(new Meal(10, 12, "Hamburger"));
        order.addMealToOrder(new Meal(6, 4, "Hot-dog"));
        order.addMealToOrder(new Meal(22, 6, "Fries"));
        order.addMealToOrder(new Meal(40, 2, "Onion rings"));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        for(int i = 0; i < order.getMeals().size(); i++) {
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {
                assertThat(calculatePrice(price,quantity), lessThan(133));
            };
            String name = "Test name: " + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);

            dynamicTests.add(dynamicTest);
        }

        return dynamicTests;
    }
    private int calculatePrice(int price, int quantity) {
        return price * quantity;
    }


}