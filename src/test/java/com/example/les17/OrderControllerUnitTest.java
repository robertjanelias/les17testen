package com.example.les17;

import com.example.les17.controller.OrderController;
import com.example.les17.dto.OrderDto;
import com.example.les17.security.JwtService;
import com.example.les17.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = true)
class OrderControllerUnitTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    OrderService orderService;

    @Test
    @WithMockUser(username="testuser", roles="USER")       // check authorization, not authentication
    void shouldRetrieveCorrectOrder() throws Exception {

        OrderDto odto = new OrderDto();
        odto.orderid = 1234;
        odto.productname = "Batavus fiets";
        odto.unitprice = 1500;
        odto.quantity = 5;

        Mockito.when(orderService.getOrder(anyInt())).thenReturn(odto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/orders/1234"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productname", is("Batavus fiets")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unitprice", is(1500.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", is(5)));
    }

    @Test
    @WithMockUser(username="testuser", roles="USER")       // check authorization, not authentication
    void shouldCalculateCorrectOrderAmount() throws Exception {

        Mockito.when(orderService.getAmount(456)).thenReturn(3001.0);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/orders/456/invoice"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderid", is(456)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", is(3001.0)));
    }
}