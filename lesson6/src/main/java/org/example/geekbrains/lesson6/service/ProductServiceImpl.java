package org.example.geekbrains.lesson6.service;

import org.example.geekbrains.lesson6.domain.Product;
import org.example.geekbrains.lesson6.domain.User;
import org.example.geekbrains.lesson6.repository.ProductJpaDAO;
import org.example.geekbrains.lesson6.repository.UserJpaDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ProductServiceImpl {
    @PersistenceContext
    private final EntityManager em;

    private final ProductJpaDAO productJpaDAO;
    private final UserJpaDAO userJpaDAO;

    public ProductJpaDAO getProductJpaDAO() {
        return productJpaDAO;
    }

    public ProductServiceImpl(EntityManager em, ProductJpaDAO productJpaDAO, UserJpaDAO userJpaDAO) {
        this.em = em;
        this.productJpaDAO = productJpaDAO;
        this.userJpaDAO = userJpaDAO;
    }

    @Transactional
    public void saveAndSet(Product product){
        Product savedProduct = productJpaDAO.save(product);
    }

    @Transactional
    public void saveAndSet(User user){
        User savedUser = userJpaDAO.save(user);
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

    public void update(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    @Transactional
    public void update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

}
