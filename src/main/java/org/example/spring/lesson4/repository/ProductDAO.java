package org.example.spring.lesson4.repository;

import org.example.spring.lesson4.domain.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    Product findById(Long id);
    Product findMaxPrice();
    Product findMinPrice();
    void save(Product product);
    void update(Product product);
    void delete(Product product);
}
