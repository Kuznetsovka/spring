package org.example.geekbrains.lesson5.repository;

import org.example.geekbrains.lesson5.domain.Product;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductJpqlDAOImpl implements ProductDAO {

    @PersistenceContext
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
    public List<Product> findByName(String name) {
        return em.createQuery("SELECT p from Product p where p.name LIKE :name", Product.class)
                .setParameter("name", name).getResultList();
    }

    @Override
    public Product findMaxPrice() {
        return em.createQuery (Product.PRODUCT_FIND_MAX_PRICE,Product.class).getSingleResult ();
    }

    @Override
    public Product findMinPrice() {
        return em.createQuery("SELECT p FROM Product p ORDER BY price ASC", Product.class)
                .getSingleResult();
    }

    public List<Product> filterMinPrice() {
        return em.createQuery("SELECT p FROM Product p ORDER BY price ASC", Product.class)
                .getResultList();
    }

    public List<Product> filterMaxPrice() {
        return em.createQuery("SELECT p FROM Product p ORDER BY price DESC", Product.class)
                .getResultList();
    }

    public List<Product> findAllByIdBetween(Double start, Double end){
        return em.createQuery("SELECT p FROM Product BETWEEN p.price=" + start + " AND p.price=" + end, Product.class)
                .getResultList();
    }

    @Override
    public void save(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.flush ();
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
