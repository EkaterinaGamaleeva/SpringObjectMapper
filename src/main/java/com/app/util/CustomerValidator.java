package com.app.util;


import com.app.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.app.services.CustomersService;

@Component
public class CustomerValidator implements Validator {

    private final CustomersService customersService;

    @Autowired

    public CustomerValidator(CustomersService customersService) {
        this.customersService = customersService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Customer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Customer customer = (Customer) o;

        if (customersService.getCustomerLastName(customer.getLastName()).isPresent()) {
            if (customersService.getCustomerFirstName(customer.getFirstName()).isPresent()) {
                errors.rejectValue("fullName", "", "Человек с таким ФИО уже существует");
            }
        }

        if (customersService.getCustomerByEmail(customer.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Человек с таким Email уже существует");
        }
    }
}