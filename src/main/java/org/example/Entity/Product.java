package org.example.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Product")
public class Product {
    public Product() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String name;
    private String skuCode;
    private double unitPrice;

    public Product(int productId, String name, String skuCode, double unitPrice) {
        this.productId = productId;
        this.name = name;
        this.skuCode = skuCode;
        this.unitPrice = unitPrice;
    }
    public Product(String name, String skuCode, double unitPrice) {
        this.name = name;
        this.skuCode = skuCode;
        this.unitPrice = unitPrice;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
