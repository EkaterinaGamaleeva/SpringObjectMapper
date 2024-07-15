package com.app.controllers;


import com.app.dto.CustomerDTO;
import com.app.dto.OrderDTO;
import com.app.models.Customer;
import jakarta.validation.Valid;
import com.app.models.Order;
import com.app.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.services.OrdersService;
import com.app.services.CustomersService;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrdersController(OrdersService ordersService,ModelMapper modelMapper) {
        this.ordersService = ordersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity getFindAll() {
        return new ResponseEntity<>( ordersService.findAll().stream().map(this::customerToOrderDto).toList(),HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable("id") int id) {
        return new ResponseEntity(customerToOrderDto(ordersService.findOne(id)),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody String s)  {
       Order order= modelMapper.map(s,Order.class);
        ordersService.save(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody String s,
                                             @PathVariable("id") int id)  {
        Order order= modelMapper.map(s,Order.class);
        ordersService.update(id,order);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        ordersService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@PathVariable("id") int id) {
        return new ResponseEntity<>(ordersService.getProducts(id),HttpStatus.OK);
    }

    public OrderDTO customerToOrderDto(Order order){
        return  modelMapper.map(order,OrderDTO.class);
    }
}