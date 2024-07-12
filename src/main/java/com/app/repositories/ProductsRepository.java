package com.app.repositories;

import com.app.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product,Integer> {
}
