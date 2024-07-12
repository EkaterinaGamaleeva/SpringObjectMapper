package com.app.services;

import com.app.models.Order;
import com.app.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.repositories.ProductsRepository;
import com.app.response.OrderCreateException;
import com.app.response.OrderNotFoundException;
import com.app.response.ProductCreateException;
import com.app.response.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Product findOne(int id) {
        Optional<Product> product = productsRepository.findById(id);
        return product.orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public void save(Product product) {
        productsRepository.save(product);
        productsRepository.findById(product.getId()).orElseThrow(ProductCreateException::new);
    }

    @Transactional
    public void update(int id, Product updatedProduct) {
        Product product = productsRepository.findById(id).get();
        updatedProduct.setId(id);
        productsRepository.save(updatedProduct);
    }

    @Transactional
    public void delete(int id) {
        productsRepository.deleteById(id);
    }

}