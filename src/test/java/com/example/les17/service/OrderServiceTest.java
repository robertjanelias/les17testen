package com.example.les17.service;

import com.example.les17.dto.OrderDto;
import com.example.les17.model.Order;
import com.example.les17.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    OrderRepository repos;

    @InjectMocks
    OrderService service;

    @Test
    @DisplayName("Should return correct order")
    void getOrder() {
        // arrange
        Order o = new Order("Television", 699, 10);
        o.setOrderid(123);

        when(repos.findById(anyInt())).thenReturn(Optional.of(o));

        // act
        OrderDto odto = service.getOrder(123);

        // assert
        assertEquals("Television", odto.productname);
        assertEquals(699, odto.unitprice);
        assertEquals(10, odto.quantity);
    }
}