package com.app.controllers;

import com.app.dto.OrderDTO;
import com.app.dto.ProductDTO;
import com.app.models.Order;
import jakarta.validation.Valid;
import com.app.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.services.CustomersService;
import com.app.services.ProductsService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductsController(ProductsService productsService, ModelMapper modelMapper) {
        this.productsService = productsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity getFindAll() {
        return new ResponseEntity<>(productsService.findAll().stream().map(this::customerToProductDto).toList(),HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable("id") int id) {
        return new ResponseEntity<>(customerToProductDto(productsService.findOne(id)),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody String s) {
        Product product=modelMapper.map(s,Product.class);
        productsService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody String s,
                                             @PathVariable("id") int id) {
        Product product=modelMapper.map(s,Product.class);
        productsService.update(id, product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        productsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ProductDTO customerToProductDto(Product product){
        return  modelMapper.map(product,ProductDTO.class);
    }
}
