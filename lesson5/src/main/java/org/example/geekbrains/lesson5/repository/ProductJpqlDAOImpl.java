package org.example.geekbrains.lesson5.repository;

import org.example.geekbrains.lesson5.domain.Product;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Product> findAllByPriceBetween(List<Product> products, Double start, Double end){
        return products.stream()
                .filter(product-> product.getPrice() >= start && product.getPrice() <= end)
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
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
