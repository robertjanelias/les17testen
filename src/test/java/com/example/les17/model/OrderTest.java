package com.example.les17.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Should keep product name")
    void shouldKeepProductName() {
        // arrange
        Order o = new Order("Television", 699, 5);

        // act
        String productName = o.getProductname();

        // assert
        assertEquals("Television", productName);
    }
}