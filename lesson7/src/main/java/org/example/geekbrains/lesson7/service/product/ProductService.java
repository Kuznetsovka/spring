package org.example.geekbrains.lesson7.service.product;

import org.example.geekbrains.lesson7.domain.Product;
import org.example.geekbrains.lesson7.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto findById(Long id);
    Product getById(Long id);
    List<ProductDto> findAll();
    ProductDto save(ProductDto dto);
    void delete(Long id);
    void update(Product dto);
    void saveAndSet(Product product);
}
