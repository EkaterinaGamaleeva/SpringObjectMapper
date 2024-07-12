package com.app.dto;

import com.app.models.Order;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.ToString;


public class ProductDTO {


 @NotEmpty
 @Size(min = 2, max = 1000, message = "Имя должно быть от 2 до 100 символов длиной")
 private  String nameProduct;


 @Size(min = 2, max = 1000, message = "детали заказа должны содержать от 2 до 100 символов длиной")
 private String  description;

 @NotNull
 @Min(value = 1, message = "Age should be greater than 1")
 private int price;

 @NotNull
 @Min(value = 1, message = "Age should be greater than 1")
 private int quantityInStock;

private Order order;


 public ProductDTO() {

 }
 public ProductDTO( String nameProduct, String description, int price, int quantityInStock) {
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

 @Override
 public String toString() {
  return '{'+"nameProduct=" + nameProduct +
          ", description=" + description  +
          ", price=" + price +
          ", quantityInStock=" + quantityInStock +
          ", order=" + order +
          '}';
 }
 public void setQuantityInStock(int quantityInStock) {
  this.quantityInStock = quantityInStock;

 }
}
