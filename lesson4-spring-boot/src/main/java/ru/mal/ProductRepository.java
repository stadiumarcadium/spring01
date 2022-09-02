package ru.mal;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = """
            select * from products u
            where (:minCostFilter is null or u.cost < :minCostFilter)
            and (:maxCostFilter is null or u.cost > :maxCostFilter)
            """, nativeQuery = true)
    Page<Product> productsByFilter(BigDecimal minCostFilter, BigDecimal maxCostFilter);
}
