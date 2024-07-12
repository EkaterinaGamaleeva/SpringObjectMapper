package com.app.dto;

import com.app.models.Order;
import jakarta.validation.constraints.*;

import java.util.List;

public class CustomerDTO {

    @NotNull
    @Size(min = 2, max = 1000, message = "Имя должно быть от 2 до 100 символов длиной")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 1000, message = "Имя должно быть от 2 до 100 символов длиной")
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotNull
    @Size(min = 2, max = 1000, message = "Имя должно быть от 2 до 100 символов длиной")
    @Pattern(regexp="^((\\+7|7|8)+([0-9]){10})$", message="не верный ноомер телефона")
    private String contactNumber;

    private List<Order> order;

    public CustomerDTO() {

    }
    public CustomerDTO(String firstName, String lastName, String email, String contactNumber, List<OrderDTO> orderDTO, List<Order> order) {
        this.order = order;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

}
