package org.example.spring.lesson4.repository;

import org.example.spring.lesson4.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaDAO extends JpaRepository<Product, Long> {
    List<Product> findAllByNameLike(String name);
    List<Product> findAllByIdBetween(Long startId, Long endId);

}
