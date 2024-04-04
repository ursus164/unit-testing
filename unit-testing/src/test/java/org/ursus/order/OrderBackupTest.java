package org.ursus.order;

import org.junit.jupiter.api.*;
import org.ursus.Meal;

import java.io.FileNotFoundException;
import java.io.IOException;

class OrderBackupTest {
    private static OrderBackup orderBackup;

    @BeforeAll
    static void setup() throws FileNotFoundException {
        // opening and initializing file - only once
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @AfterAll
    static void tearDown() throws IOException {
        // closing file
        orderBackup.closeFile();
    }

    @BeforeEach
    void appendToOrderBackup() throws IOException {
        // appending to file
        orderBackup.getWriter().append("New order: ");
        // appending only "New order: " to the file
    }

    @AfterEach
    void clearOrderBackup() throws IOException {
        // clearing file
        orderBackup.getWriter().append(" - backed up");
        // adding " - backed up" to the file
    }

    @Test
    void backupOrderWithOneMeal() throws IOException {
        // given
        Meal meal = new Meal(10, "Burger");
        org.ursus.order.Order order = new Order();
        order.addMealToOrder(meal);

        // when
        orderBackup.backupOrder(order);
        // appending order to the file

        // then
        System.out.println("Order : " + order.toString() + " backed up");
    }



    @Test
    void createFile() {
    }

    @Test
    void backupOrder() {
    }

    @Test
    void closeFile() {
    }
}