package org.example.Specification;

import jakarta.persistence.criteria.Join;
import org.example.Entity.OrderLine;
import org.example.Entity.Orders;
import org.example.Entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Orders> hasProduct(String productName) {
        return (root, query, builder) -> {
            Join<Orders, OrderLine> orderLineJoin = root.join("orderLines");
            Join<OrderLine, Product> productJoin = orderLineJoin.join("productId");

            return builder.equal(productJoin.get("name"), productName);
        };
    }
}
