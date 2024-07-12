package com.app.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Customer owner;
    @NotEmpty(message = "Не верно указана дата")
    @Column(name = "orderDate")
    private Date orderDate;
    @NotEmpty(message = "Пустой адрес доставки")
    @Column(name = "shippingAddress")
    private String shippingAddress;

    @NotEmpty
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Product> productList;
    @NotEmpty(message = "Не корректный статус заказа")
    @Column(name = "orderStatus")
    private Status orderStatus;
    @NotNull(message = "Укажите сумму заказа")
    @Column(name = "totalPrice")
    @Min(value = 1, message = "Age should be greater than 100")
    private int totalPrice;
    public Order() {
    }

    public Order(int id, Customer owner, Date orderDate, String shippingAddress, List<Product> productList, Status orderStatus, int totalPrice) {
        this.id = id;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.productList = productList;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.owner=owner;
    }


    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}