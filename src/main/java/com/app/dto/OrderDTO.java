package com.app.dto;


import com.app.models.Customer;
import com.app.models.Product;
import com.app.models.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private CustomerDTO customerDTO;

    @NotEmpty(message = "Пустой адрес доставки")
    private String shippingAddress;

    @NotEmpty
    private List<ProductDTO> productList;

    @NotNull(message = "Укажите сумму заказа")
    @Min(value = 1, message = "Age should be greater than 100")
    private int totalPrice;

    public OrderDTO() {
    }

    public OrderDTO( CustomerDTO customerDTO, String shippingAddress, List<ProductDTO> productDTOList, Status orderStatus, int totalPrice) {
        this.customerDTO=customerDTO;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.productList=productDTOList;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }


    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}