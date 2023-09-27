package org.example.ServiceTest;

import org.example.Entity.Customer;
import org.example.Repository.CustomerRepository;
import org.example.Service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> mockCustomers = new ArrayList<>();
        mockCustomers.add(new Customer("21213","Jack Sparrow", "jack@mailcom", "034029302"));
        mockCustomers.add(new Customer("55232","Tom Jackson", "tom@mailcom", "536421312"));

        when(customerRepository.findAll()).thenReturn(mockCustomers);
        List<Customer> result = customerService.getAllCustomers();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Jack Sparrow", result.get(0).getFullName());
        Assertions.assertEquals("Tom Jackson", result.get(1).getFullName());
    }

    @Test
    public void testGetCustomerById() {
        Customer mockCustomer = new Customer(1, "21213", "Jack Sparrow", "jack@mailcom", "034029302");
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(mockCustomer));
        Customer result = customerService.getCustomerById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getCustomerId());
        Assertions.assertEquals("21213", result.getRegistrationCode());
        Assertions.assertEquals("Jack Sparrow", result.getFullName());
        Assertions.assertEquals("jack@mailcom", result.getEmail());
        Assertions.assertEquals("034029302", result.getTelephone());
    }

    @Test
    public void testGetCustomerByIdNotFound() {
        Mockito.when(customerRepository.findById(2L)).thenReturn(Optional.empty());
        Customer result = customerService.getCustomerById(2L);
        Assertions.assertNull(result);
    }

    @Test
    public void testCreateCustomer() {
        Customer newCustomer = new Customer("55232","Tom Jackson", "tom@mailcom", "536421312");
        when(customerRepository.save(newCustomer)).thenReturn(newCustomer);
        Customer result = customerService.createCustomer(newCustomer);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Tom Jackson", result.getFullName());
    }
}
