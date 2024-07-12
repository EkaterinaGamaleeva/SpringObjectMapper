package com.app.controllers;

import com.app.dto.CustomerDTO;
import com.app.models.Customer;
import com.app.models.Order;
import com.app.repositories.CustomersRepository;
import com.app.services.CustomersService;
import com.app.util.CustomerValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomersController.class)
class CustomersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomersService service;
    @MockBean
    private CustomersRepository repository;
    @MockBean
    private CustomerValidator customerValidator;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    void getUsers() throws Exception {
        Customer customer =new Customer(1,"ЕКатерина","Гамалеева","kate_gamaleeva@icloud.com","89959898265");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        Mockito.when(service.findAll()).thenReturn(customerList);
        var a= mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].firstName").value(customer.getFirstName()))
           .andExpect(jsonPath("[0].lastName").value(customer.getLastName()))
               .andExpect(jsonPath("[0].email").value(customer.getEmail()))
               .andExpect( jsonPath("[0].contactNumber").value(customer.getContactNumber()))
                .andExpect( jsonPath("[0].order").value(customer.getOrder()))
                .andReturn();

    }

    @Test
    void getCustomerById() throws Exception {
        Customer customer =new Customer(1,"ЕКатерина","Гамалеева","kate_gamaleeva@icloud.com","89959898265");
        Mockito.when(service.findOne(1)).thenReturn(customer);
        var a= mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".firstName").value(customer.getFirstName()))
              .andExpect(jsonPath(".lastName").value(customer.getLastName()))
                .andExpect(jsonPath(".email").value(customer.getEmail()))
                .andExpect( jsonPath(".contactNumber").value(customer.getContactNumber()))
                .andExpect( jsonPath(".order").value(customer.getOrder()))
                .andReturn();
    }

    @Test
    void create() throws Exception {
        Customer customer =new Customer(1,"ЕКатерина","Гамалеева","kate_gamaleeva@icloud.com","89959898265", List.of(new Order()));
        Mockito.when(repository.save(Mockito.any())).thenReturn(customer);
        mockMvc.perform(post("/customers")
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}