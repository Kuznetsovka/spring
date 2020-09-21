package org.example.geekbrains.lesson6.service;

import javax.persistence.EntityManager;

public class RepoUtil {

    public static <T> void createEntity(EntityManager em, T entity){

        System.out.println("Creating entity");
        //open transaction
        em.getTransaction().begin();
        //put person into persist area of Hibernate
        em.persist(entity);
        //commit/close transaction
        em.getTransaction().commit();

        System.out.println("Creating finished");

    }

    public static <T> T readEntity(EntityManager em, Class<T> clazz, long id){
        System.out.println("Start reading");

        em.getTransaction().begin();
        T entity = em.find(clazz, id);
        em.getTransaction().commit();

        System.out.println("Reading completed->" + entity);
        return entity;
    }

    public static <T> T saveEntity(EntityManager em, T entity){
        System.out.println("Start saving");

        em.getTransaction().begin();
        T savedEntity = em.merge(entity);
        em.getTransaction().commit();

        System.out.println("Saving completed->" + savedEntity);

        return savedEntity;
    }
}
