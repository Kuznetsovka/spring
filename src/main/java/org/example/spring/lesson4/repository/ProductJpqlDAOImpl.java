package org.example.spring.lesson4.repository;

import org.example.spring.lesson4.domain.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductJpqlDAOImpl implements ProductDAO {

    private final EntityManager em;

    public ProductJpqlDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("SELECT p from Product p", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id) {
        return em.createQuery("SELECT p from Product p where p.id = :id", Product.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public Product findMaxPrice() {
        return em.createQuery("SELECT p FROM Products p ORDER BY price_fld DESC LIMIT 1", Product.class)
                .getSingleResult();
    }

    @Override
    public Product findMinPrice() {
        return em.createQuery("SELECT p FROM Products p ORDER BY price_fld ASC LIMIT 1", Product.class)
                .getSingleResult();
    }

    @Override
    public void save(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    @Override
    public void update(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Product product) {
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }
}
