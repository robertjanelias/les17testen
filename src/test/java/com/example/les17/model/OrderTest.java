package com.example.les17.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    @DisplayName("Should keep productname")
    public void test1() {
        // arrange
        Order o = new Order("Quest 2 VR Headset", 399.99, 17);

        // act
        String result = o.getProductname();

        // assert
        assertEquals("Quest 2 VR Headset", result);
    }

}