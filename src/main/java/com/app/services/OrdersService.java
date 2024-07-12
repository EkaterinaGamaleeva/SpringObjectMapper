package com.app.services;




import com.app.models.Order;
import com.app.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.repositories.OrdersRepository;
import com.app.response.OrderCreateException;
import com.app.response.OrderNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    public Order findOne(int id) {
        Optional<Order> foundBook = ordersRepository.findById(id);
        return foundBook.orElseThrow(OrderNotFoundException::new);
    }

    @Transactional
    public void save(Order order) {
        ordersRepository.save(order);
       ordersRepository.findById(order.getId()).orElseThrow(OrderCreateException::new);
    }

    @Transactional
    public void update(int id, Order updatedOrder) {
        Order order = ordersRepository.findById(id).get();
        updatedOrder.setId(id);
        updatedOrder.setOwner(order.getOwner()); //добавляем владельца заказа
        updatedOrder.setProductList(order.getProductList()); //добавляем продуккты
        ordersRepository.save(updatedOrder);
    }

    @Transactional
    public void delete(int id) {
        ordersRepository.deleteById(id);
    }



    public List<Product> getProducts(int id) {
       return ordersRepository.findById(id).orElseThrow(OrderNotFoundException::new).getProductList();
    }

}
