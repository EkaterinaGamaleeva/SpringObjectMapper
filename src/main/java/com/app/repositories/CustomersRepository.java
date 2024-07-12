package com.app.repositories;

import com.app.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer>{
    Optional<Customer> findByFirstName(String firstName);
    Optional<Customer> findByLastName(String LastName);
    Optional<Customer> findByEmail(String email);
}
