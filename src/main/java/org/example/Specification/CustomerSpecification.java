package org.example.Specification;

import org.example.Entity.Customer;
import org.example.Entity.Orders;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {
    public static Specification<Orders> hasCustomer(Customer customer) {
        return (root, query, builder) -> {
            return builder.equal(root.get("customerId"), customer);
        };
    }
}
