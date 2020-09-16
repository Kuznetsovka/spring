package org.example.spring.lesson3;

import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory entityFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = entityFactory.createEntityManager();

        Customer customer = new Customer ();
        customer.setFirstname("Vasiliy");
        customer.setLastname("Ivanov");

        createEntity(em, customer);

        Customer readEntity = readEntity(em, 1L);
        readEntity.setFirstname("New Name");

        saveEntity(em, readEntity);
    }

    private static void createEntity(EntityManager em, Customer entity){

        System.out.println("Creating entity");
        //open transaction
        em.getTransaction().begin();
        //put person into persist area of Hibernate
        em.persist(entity);
        //commit/close transaction
        em.getTransaction().commit();

        System.out.println("Creating finished");

    }

    private static Customer readEntity(EntityManager em, long id){
        System.out.println("Start reading");

        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
        em.getTransaction().commit();

        System.out.println("Reading completed->" + customer);
        return customer;
    }

    private static void saveEntity(EntityManager em, Customer entity){
        System.out.println("Start saving");

        em.getTransaction().begin();
        Customer savedEntity = em.merge(entity);
        em.getTransaction().commit();

        System.out.println("Saving completed->" + savedEntity);
    }
}
