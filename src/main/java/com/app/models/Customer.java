package com.app.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.app.util.View;

import java.util.List;
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "firstName")
    @Size(min = 2, max = 1000, message = "Имя должно быть от 2 до 100 символов длиной")
    private String firstName;

    @NotEmpty
    @Column(name = "lastName")
    @Size(min = 2, max = 1000, message = "Имя должно быть от 2 до 100 символов длиной")
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Column(name = "email")
    @Email
    private String email;

    @NotEmpty
    @Column(name = "contactNumber")
    @Size(min = 2, max = 1000, message = "Имя должно быть от 2 до 100 символов длиной")
    @Pattern(regexp="^((\\+7|7|8)+([0-9]){10})$", message="не верный ноомер телефона")
    private String contactNumber;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="order_id",referencedColumnName = "id")
    private List<Order> order;

    public Customer() {

    }
    public Customer(int id, String firstName, String lastName, String email, String contactNumber, List<Order> order) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.order = order;
    }
    public Customer(int id, String firstName, String lastName, String email, String contactNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
