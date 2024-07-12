package com.app.controllers;

import com.app.models.Customer;
import com.app.models.Order;
import com.app.models.Product;
import com.app.models.Status;
import com.app.repositories.CustomersRepository;
import com.app.repositories.ProductsRepository;
import com.app.services.CustomersService;
import com.app.services.ProductsService;
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

@WebMvcTest(ProductsController.class)
class ProductsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductsService service;
    @MockBean
    private ProductsRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    void getFindAll() throws Exception {
        Product product=new  Product(1,"zsczsc","zsczsv",2,25546);
        List<Product> products = new ArrayList<>();
        products.add(product);
        Mockito.when(service.findAll()).thenReturn(products);
        var a= mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].nameProduct").value(product.getNameProduct()))
                .andExpect(jsonPath("[0].description").value(product.getDescription()))
              .andExpect(jsonPath("[0].price").value(product.getPrice()))
                .andExpect( jsonPath("[0].quantityInStock").value(product.getQuantityInStock()))
                .andReturn();
    }

    @Test
    void findOne() throws Exception {
        Product product=new  Product(1,"zsczsc","zsczsv",2,25546);
        Mockito.when(service.findOne(1)).thenReturn(product);
        var a= mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".nameProduct").value(product.getNameProduct()))
                .andExpect(jsonPath(".description").value(product.getDescription()))
                .andExpect(jsonPath(".price").value(product.getPrice()))
                .andExpect( jsonPath(".quantityInStock").value(product.getQuantityInStock()))
                .andReturn();

    }
    @Test
    void create() throws Exception {

        Product product=new  Product(1,"zsczsc","zsczsv",2,25546);
        Mockito.when(repository.save(Mockito.any())).thenReturn(product);
        mockMvc.perform(post("/products")
                        .content(objectMapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}