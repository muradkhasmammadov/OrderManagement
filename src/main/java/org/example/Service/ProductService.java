package org.example.Service;

import org.example.Entity.Product;
import org.example.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {


    private ProductRepository productRepository;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ProductService(ProductRepository productRepository, JdbcTemplate jdbcTemplate) {
        this.productRepository = productRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public void generateAndInsertTestData() {
        try {
            ClassPathResource sqlResource = new ClassPathResource("mock_data.sql");
            String sqlData = StreamUtils.copyToString(sqlResource.getInputStream(), StandardCharsets.UTF_8);

            String[] sqlStatements = sqlData.split(";");

            for (String sqlStatement : sqlStatements) {
                if (!sqlStatement.trim().isEmpty()) {
                    jdbcTemplate.execute(sqlStatement);
                }
            }
            System.out.println("Data inserted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading SQL data.");
        }
    }

    private List<Product> parseCsvToProductList(String csvData) {
        List<Product> products = new ArrayList<>();
        String[] lines = csvData.split("\n");

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String name = parts[0].trim();
                String skuCode = parts[1].trim();
                double unitPrice = Double.parseDouble(parts[2].trim());
                Product product = new Product(name, skuCode, unitPrice);
                products.add(product);
            }
        }

        return products;
    }
   }

