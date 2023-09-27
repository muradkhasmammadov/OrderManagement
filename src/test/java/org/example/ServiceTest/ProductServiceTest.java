package org.example.ServiceTest;

import org.example.Entity.Product;
import org.example.Repository.ProductRepository;
import org.example.Service.ProductService;
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

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product(1, "Product1", "SKU001", 10.99));
        mockProducts.add(new Product(2, "Product2", "SKU002", 15.99));

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> result = productService.getAllProducts();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Product1", result.get(0).getName());
        Assertions.assertEquals("Product2", result.get(1).getName());
    }
    @Test
    public void testGetProductById() {
        Product mockProduct = new Product(1, "Product1", "SKU001", 10.99);
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        Product result = productService.getProductById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getProductId());
        Assertions.assertEquals("Product1", result.getName());
    }

    @Test
    public void testCreateProduct() {
        Product newProduct = new Product("New Product", "SKU002", 19.99);
        when(productRepository.save(newProduct)).thenReturn(newProduct);
        Product result = productService.createProduct(newProduct);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("New Product", result.getName());
    }
}
