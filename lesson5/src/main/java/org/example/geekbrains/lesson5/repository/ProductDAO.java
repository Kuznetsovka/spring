package org.example.geekbrains.lesson5.repository;

import org.example.geekbrains.lesson5.domain.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    Product findById(Long id);
    List<Product> findByName(String name);
    Product findMaxPrice();
    Product findMinPrice();
    void save(Product product);
    void update(Product product);
    void delete(Product product);
}
