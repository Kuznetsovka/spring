package org.example.geekbrains.lesson7.dao;

import org.example.geekbrains.lesson7.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
    List<Product> findAllByName(String name);
    List<Product> findAllById(Long id);
    List<Product> findAllByIdBetween(Long startId, Long endId);
    List<Product> findAllByPriceBetween(Double priceFrom, Double priceTo);
    List<Product> findAllByNameLike(String name);

    @Override
    void deleteById(Long aLong);
}
