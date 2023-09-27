package org.example.ServiceTest;

import org.example.Entity.Customer;
import org.example.Entity.OrderLine;
import org.example.Entity.Orders;
import org.example.Entity.Product;
import org.example.Repository.OrderRepository;
import org.example.Service.OrderService;
import org.example.Specification.CustomerSpecification;
import org.example.Specification.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductSpecification productSpecification;

    @Mock
    private CustomerSpecification customerSpecification;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        List<Orders> mockOrders = new ArrayList<>();
        mockOrders.add(new Orders(new Customer(), new Date()));
        mockOrders.add(new Orders(new Customer(), new Date()));
        when(orderRepository.findAll()).thenReturn(mockOrders);
        List<Orders> result = orderService.getAllOrders();

        assertEquals(2, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrderById() {
        List<OrderLine> testOrderLines = new ArrayList<>();
        testOrderLines.add(new OrderLine(new Product(), 100));
        Orders mockOrder = new Orders(new Customer(), testOrderLines, new Date());
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));
        Orders result = orderService.getOrderById(1L);

        assertEquals(mockOrder, result);
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateOrder() {
        List<OrderLine> testOrderLines = new ArrayList<>();
        testOrderLines.add(new OrderLine(new Product(), 100));
        Orders mockOrder = new Orders(new Customer(), testOrderLines, new Date());
        when(orderRepository.save(mockOrder)).thenReturn(mockOrder);
        Orders result = orderService.createOrder(mockOrder);
        assertEquals(mockOrder, result);
        verify(orderRepository, times(1)).save(mockOrder);
    }
}
