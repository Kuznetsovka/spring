package org.example.geekbrains.lesson5.service;

import org.example.geekbrains.lesson5.domain.Product;
import org.example.geekbrains.lesson5.repository.ProductJpaDAO;
import org.example.geekbrains.lesson5.repository.ProductJpqlDAOImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl {

    private final ProductJpaDAO productJpaDAO;
    private final ProductJpqlDAOImpl productJpaDAOImpl;

    public ProductJpaDAO getProductJpaDAO() {
        return productJpaDAO;
    }

    public ProductJpqlDAOImpl getProductJpaDAOImpl() {
        return productJpaDAOImpl;
    }

    public ProductServiceImpl(ProductJpaDAO productJpaDAO, ProductJpqlDAOImpl productJpaDAOImpl) {
        this.productJpaDAO = productJpaDAO;
        this.productJpaDAOImpl = productJpaDAOImpl;
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

    @Transactional
    public void delete(Long id){
        productJpaDAO.deleteById (id);
    }

}
