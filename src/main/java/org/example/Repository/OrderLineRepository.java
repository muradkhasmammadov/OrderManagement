package org.example.Repository;

import org.example.Entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
