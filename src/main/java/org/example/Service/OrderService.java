package org.example.Service;

import org.example.Entity.Orders;
import org.example.Entity.Customer;
import org.example.Repository.OrderRepository;
import org.example.Specification.CustomerSpecification;
import org.example.Specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Orders getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<Orders> getOrderByDateOfSubmission(Date dateOfSubmission) {
        return orderRepository.findByDateOfSubmission(dateOfSubmission);
    }

    public List<Orders> getOrdersByProduct(String productName) {
        Specification<Orders> spec = ProductSpecification.hasProduct(productName);
        return orderRepository.findAll(spec);
    }

    public List<Orders> getOrdersByCustomer(Customer customerId) {
        Specification<Orders> spec = CustomerSpecification.hasCustomer(customerId);
        return orderRepository.findAll(spec);
    }
    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

}
