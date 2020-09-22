package org.example.geekbrains.lesson6.repository;

import org.example.geekbrains.lesson6.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaDAO extends JpaRepository<Product, Long> {
    List<Product> findAllByName(String name);
    List<Product> findAllById(Long id);
    List<Product> findAllByIdBetween(Long startId, Long endId);
    List<Product> findAllByPriceBetween(Double priceFrom, Double priceTo);
    List<Product> findAllByNameLike(String name);
    List<Product> findByName(String name, Pageable page);
    Page<Product> findAll(Pageable pageable);

    @Override
    void deleteById(Long aLong);
}
