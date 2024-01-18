package com.example.les17.service;

import com.example.les17.dto.OrderDto;
import com.example.les17.model.Order;
import com.example.les17.repository.OrderRepository;
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
    public void shouldReturnCorrectOrder() {
        // arrange
        Order o = new Order("Quest 2 VR Headset", 399.99, 17);
        o.setOrderid(1);

        when(repos.findById(anyInt())).thenReturn(Optional.of(o));

        // act
        OrderDto orderDto = service.getOrder(1);

        // assert
        assertEquals(1, orderDto.orderid);
        assertEquals("Quest 2 VR Headset", orderDto.productname);
        assertEquals(399.99, orderDto.unitprice);
        assertEquals(17, orderDto.quantity);

    }

}