package com.app.services;
import com.app.models.Customer;
import com.app.models.Order;
import com.app.repositories.CustomersRepository;
import com.app.response.CustomerCreateException;
import com.app.response.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomersService {
    private CustomersRepository customersRepository;

    @Autowired
    public CustomersService(CustomersRepository customersRepository) {

        this.customersRepository = customersRepository;
    }

    public List<Customer> findAll() {

        return customersRepository.findAll();
    }

    public Customer findOne(int id) {
        Optional<Customer> customer = customersRepository.findById(id);
        return customer.orElseThrow(CustomerNotFoundException::new);
    }

    @Transactional
    public void save(Customer customer) {
        customersRepository.save(customer);
        customersRepository.findById(customer.getId()).orElseThrow(CustomerCreateException::new);
        ;
    }

    @Transactional
    public void update(int id, Customer updatedCustomer) {
        updatedCustomer.setId(id);
        customersRepository.save(updatedCustomer);
    }

    @Transactional
    public void delete(int id) {
        customersRepository.deleteById(id);
    }

    public Optional<Customer> getCustomerFirstName(String ferst) {

        return customersRepository.findByFirstName(ferst);
    }
    public Optional<Customer> getCustomerLastName(String last) {

        return customersRepository.findByLastName(last);
    }

    public Optional<Customer> getCustomerByEmail(String email) {

        return customersRepository.findByEmail(email);
    }

    public List<Order> getOrdersByCustomerId(int id) {
       return customersRepository.findById(id).orElseThrow(CustomerNotFoundException::new).getOrder();
    }
}