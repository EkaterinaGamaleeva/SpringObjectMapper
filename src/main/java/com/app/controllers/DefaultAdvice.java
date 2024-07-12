package com.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.app.response.*;

@RestControllerAdvice()
public class DefaultAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        return new ResponseEntity<>(ErrorResponse.create(e, HttpStatus.BAD_REQUEST, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<CustomerErrorResponse> handlerException(CustomerNotFoundException e) {
        CustomerErrorResponse response = new CustomerErrorResponse("Человек с таким id не найден", System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<CustomerErrorResponse> handlerException(CustomerCreateException e) {
        CustomerErrorResponse response = new CustomerErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<OrderErrorResponse> handlerException(OrderNotFoundException e) {
        OrderErrorResponse response = new OrderErrorResponse("Заказ с таким id не найдем", System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<OrderErrorResponse> handlerException(OrderCreateException e) {
        OrderErrorResponse response = new OrderErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<ProductErrorResponse> handlerException(ProductNotFoundException e) {
        ProductErrorResponse response = new ProductErrorResponse("Продукт с таким id не найдем", System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ProductErrorResponse> handlerException(ProductCreateException e) {
        ProductErrorResponse response = new ProductErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}