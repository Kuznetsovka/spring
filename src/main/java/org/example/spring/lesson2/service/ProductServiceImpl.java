package org.example.spring.lesson2.service;

import org.example.spring.lesson2.domain.Product;
import org.example.spring.lesson2.repository.ProductRepoImpl;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl {

    private ProductRepoImpl productRepo;

    public ProductServiceImpl(ProductRepoImpl productRepo) {
        this.productRepo = productRepo;
    }

    public Product getById(Long id){
        return productRepo.getById(id);
    }

    public List<Product> getAll(){
        List<Product> products = productRepo.getAll();
        products.sort(Comparator.comparingLong(Product::getId));
        return products;
    }

    public List<Product> getByPrice(Double start, Double end){
        return productRepo.getAll().stream()
                .filter(product-> product.getPrice() >= start && product.getPrice() <= end)
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    public Product save(Product product){
        return productRepo.save(product);
    }

    public List<Product> removeById(Long id){
            productRepo.remove(id);
        return productRepo.getAll();
    }



}
