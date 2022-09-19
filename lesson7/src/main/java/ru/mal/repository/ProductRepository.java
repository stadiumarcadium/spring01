package ru.mal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.mal.model.Product;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    Page<Product> findAllByTitleLike (String titleFilter, Pageable pageable);

    @Query(value = """
            select * from products u
            where (:titleFilter is null or u.title like :titleFilter)
            and (:minCostFilter is null or u.cost >= :minCostFilter)
            and (:maxCostFilter is null or u.cost <= :maxCostFilter)
            """,
            countQuery = """
            select count(*) from products u
            where (:titleFilter is null or u.title like :titleFilter)
            and (:minCostFilter is null or u.cost >= :minCostFilter)
            and (:maxCostFilter is null or u.cost <= :maxCostFilter)
            """,
            nativeQuery = true)
    Page<Product> productsByFilter(String titleFilter, BigDecimal minCostFilter, BigDecimal maxCostFilter,Pageable pageable);
}
