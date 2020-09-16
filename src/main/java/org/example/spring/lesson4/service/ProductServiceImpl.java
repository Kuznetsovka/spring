package org.example.spring.lesson4.service;

import org.example.spring.lesson4.domain.Product;
import org.example.spring.lesson4.repository.ProductJpaDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl {

    private final ProductJpaDAO productJpaDAO;

    public ProductServiceImpl(ProductJpaDAO productJpaDAO) {
        this.productJpaDAO = productJpaDAO;
    }

    @Transactional
    public void saveAndSet(Product product){
        Product savedProduct = productJpaDAO.save(product);
    }

    @Transactional(readOnly = true)
    public Product findById(Long id){
        return productJpaDAO.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public String findNameById(Long id){
        return productJpaDAO.findById(id).orElse(null).getName ();
    }

}
