package org.ursus.intro;

import org.junit.jupiter.api.Test;

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
}