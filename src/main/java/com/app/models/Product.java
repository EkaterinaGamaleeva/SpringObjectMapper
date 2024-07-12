package com.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.ToString;

@Entity
@Table(name = "products")
@ToString
public class Product {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id")
 private int id;

 @NotEmpty
 @Column(name = "nameProduct")
 @Size(min = 2, max = 1000, message = "Имя должно быть от 2 до 100 символов длиной")
 private  String nameProduct;

 @Column(name = "description")
 @Size(min = 2, max = 1000, message = "Имя должно быть от 2 до 100 символов длиной")
 private String  description;

 @NotNull
 @Column(name = "price")
 @Min(value = 1, message = "Age should be greater than 1")
 private int price;

 @NotNull
 @Column(name = "quantityInStock")
 @Min(value = 1, message = "Age should be greater than 1")
 private int quantityInStock;


 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "order_id", referencedColumnName = "id")
 private Order order;

 public Product() {

 }
 public Product(int id, String nameProduct, String description, int price, int quantityInStock, Order order) {
  this.id = id;
  this.nameProduct = nameProduct;
  this.description = description;
  this.price = price;
  this.quantityInStock = quantityInStock;
  this.order = order;
 }

 public Product(int id, String nameProduct, String description, int price, int quantityInStock) {
  this.id = id;
  this.nameProduct = nameProduct;
  this.description = description;
  this.price = price;
  this.quantityInStock = quantityInStock;
 }


 public Order getOrder() {
  return order;
 }

 public void setOrder(Order order) {
  this.order = order;
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getNameProduct() {
  return nameProduct;
 }

 public void setNameProduct(String nameProduct) {
  this.nameProduct = nameProduct;
 }

 public String getDescription() {
  return description;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public int getPrice() {
  return price;
 }

 public void setPrice(int price) {
  this.price = price;
 }

 public int getQuantityInStock() {
  return quantityInStock;
 }

 public void setQuantityInStock(int quantityInStock) {
  this.quantityInStock = quantityInStock;
 }
}
