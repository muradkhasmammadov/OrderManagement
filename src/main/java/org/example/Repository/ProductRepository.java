package org.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
