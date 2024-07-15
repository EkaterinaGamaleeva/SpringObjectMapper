package com.app.controllers;


import com.app.dto.CustomerDTO;
import jakarta.validation.Valid;
import com.app.models.Customer;

import com.app.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.app.services.CustomersService;
import com.app.util.CustomerValidator;


import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomersController {
    private final CustomersService customersService;
    private final CustomerValidator customerValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomersController(CustomersService customersService, ModelMapper modelMapper,CustomerValidator customerValidator) {
        this.customersService = customersService;
        this.customerValidator=customerValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity getUsers() {
        return new ResponseEntity<>(customersService.findAll().stream().map(this::customerToCustomerDto).toList(),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable("id") int id) {
        return new ResponseEntity<>(customerToCustomerDto(customersService.findOne(id)),HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpStatus> create(@RequestBody String s, BindingResult bindingResult) {
        Customer customer=modelMapper.map(s,Customer.class);
        customerValidator.validate(customer,bindingResult);
        customersService.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid String s, BindingResult bindingResult,
                                 @PathVariable("id") int id) {
        Customer customer=modelMapper.map(s,Customer.class);
        customerValidator.validate(customer,bindingResult);
        customersService.update(id, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        customersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders(@PathVariable("id") int id) {
        return new ResponseEntity<>(customersService.getOrdersByCustomerId(id),HttpStatus.OK);
    }

    public CustomerDTO customerToCustomerDto(Customer customer){
        return  modelMapper.map(customer,CustomerDTO.class);
    }
}
