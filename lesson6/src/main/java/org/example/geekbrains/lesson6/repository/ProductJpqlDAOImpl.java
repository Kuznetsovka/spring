//package org.example.geekbrains.lesson6.repository;
//
//import org.example.geekbrains.lesson6.domain.Product;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Service
//public class ProductJpqlDAOImpl implements ProductDAO {
//
//    @PersistenceContext
//    private final EntityManager em;
//
//    public ProductJpqlDAOImpl(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public void save(Product product) {
//        em.getTransaction().begin();
//        em.persist(product);
//        em.flush ();
//        em.getTransaction().commit();
//    }
//
//    @Override
//    public void update(Product product) {
//        em.getTransaction().begin();
//        em.merge(product);
//        em.getTransaction().commit();
//    }
//
//    @Override
//    public void delete(Product product) {
//        em.getTransaction().begin();
//        em.remove(product);
//        em.getTransaction().commit();
//    }
//}
