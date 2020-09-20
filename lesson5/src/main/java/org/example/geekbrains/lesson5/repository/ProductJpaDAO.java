package org.example.geekbrains.lesson5.repository;

import org.example.geekbrains.lesson5.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public interface ProductJpaDAO extends JpaRepository<Product, Long> {
    List<Product> findAllByName(String name);
    List<Product> findAllByIdBetween(Long startId, Long endId);
    List<Product> findAllByPriceBetween(Double priceFrom, Double priceTo);
    Product findByNameLike(String name);

    @Override
    void deleteById(Long aLong);
}
