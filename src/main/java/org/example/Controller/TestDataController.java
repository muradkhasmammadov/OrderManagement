package org.example.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.Service.ProductService;

@RestController
@RequestMapping("/api/data-generation")
public class TestDataController {

    private final ProductService productService;

    public TestDataController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/generate")
    public void generateTestData() {
        productService.generateAndInsertTestData();
    }
}
