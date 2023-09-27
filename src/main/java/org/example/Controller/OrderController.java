package org.example.Controller;

import org.example.Entity.Customer;
import org.example.Entity.Orders;
import org.example.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Orders getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }


    @GetMapping("/date/{dateOfSubmission}")
    public List<Orders> getOrdersByDateOfSubmission(@PathVariable("dateOfSubmission") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfSubmission) {
        return orderService.getOrderByDateOfSubmission(dateOfSubmission);
    }

    @GetMapping("/byProduct")
    public List<Orders> getOrdersByProduct(@RequestParam String productName) {
        return orderService.getOrdersByProduct(productName);
    }

    @GetMapping("/byCustomer")
    public List<Orders> getOrdersByCustomer(@RequestParam int customerId) {
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        return orderService.getOrdersByCustomer(customer);
    }

    @PostMapping
    public Orders createOrder(@RequestBody Orders order) {
        return orderService.createOrder(order);
    }
}
