package com.app.controllers;

import com.app.dto.CustomerDTO;
import com.app.dto.ProductDTO;
import com.app.models.Customer;
import com.app.models.Order;
import com.app.models.Product;
import com.app.models.Status;
import com.app.repositories.CustomersRepository;
import com.app.repositories.OrdersRepository;
import com.app.services.CustomersService;
import com.app.services.OrdersService;
import com.app.util.CustomerValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrdersController.class)
class OrdersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrdersService service;
    @MockBean
    private OrdersRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Test
    void getFindAll() throws Exception {
       Order order =new Order(1,new Customer(),new Date(),"fcfgfgfdcgf",List.of(new Product()), Status.EXPECTED,12565);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        Mockito.when(service.findAll()).thenReturn(orders);
        var a= mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
             .andExpect(jsonPath("[0].customerDTO").value(order.getOwner().getFirstName()))
                .andExpect(jsonPath("[0].shippingAddress").value(order.getShippingAddress()))
//               .andExpect(jsonPath("[0].productList").value(objectMapper.writeValueAsString(order.getProductList().stream().map(e->modelMapper.map(e,ProductDTO.class)).toList()))
                .andExpect( jsonPath("[0].totalPrice").value(order.getTotalPrice()))
                .andReturn();
    }

    @Test
    void findOne() throws Exception {
        Order order =new Order(1,new Customer(),new Date(),"fcfgfgfdcgf",List.of(new Product()), Status.EXPECTED,12565);
        Mockito.when(service.findOne(1)).thenReturn(order);
        var a= mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".customerDTO").value(order.getOwner().getContactNumber()))
                .andExpect(jsonPath(".shippingAddress").value(order.getShippingAddress()))
//                .andExpect(jsonPath(".productList").value(objectMapper.writeValueAsString(order.getProductList().stream().map(e->modelMapper.map(e,ProductDTO.class)).toList())))
                .andExpect( jsonPath(".totalPrice").value(order.getTotalPrice()))
                .andReturn();
    }
    @Test
    void create() throws Exception {
        Order order =new Order(1,new Customer(1,"ЕКатерина","Гамалеева","kate_gamaleeva@icloud.com","89959898265"),new Date(),"fcfgfgfdcgf",List.of(new Product(1,"kjkjbkjb","jhbjhb",2,54565)), Status.EXPECTED,12565);
        Mockito.when(repository.save(Mockito.any())).thenReturn(order);
        mockMvc.perform(post("/orders")
                        .content(objectMapper.writeValueAsString(order))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}