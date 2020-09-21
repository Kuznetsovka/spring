package org.example.geekbrains.lesson6;

import org.example.geekbrains.lesson6.domain.Product;
import org.example.geekbrains.lesson6.domain.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class InitData {

    private static long ind = 99;

    private static ArrayList<Product> products = new ArrayList<> ();
    private static ArrayList<User> users = new ArrayList<> ();

    public static final double COUNT_PRODUCTS = 10;
    public static final double COUNT_USERS = 10;

    static {
        int i = 0;
        for (int j = 0; j < COUNT_PRODUCTS; j++) {
            Product product = new Product ();
            product.setName ("Product_" + i++);
            product.setPrice ( Math.random ()*1000);
            products.add (product);
        }
        i = 0;
        for (int j = 0; j < COUNT_PRODUCTS; j++) {
            User user = new User ();
            user.setName ("User_" + i++);
            user.setEmail (user.getName () + "@mail.ru");
            user.setAge ((int) (Math.random ()*100));
            GregorianCalendar gc = new GregorianCalendar();
            gc.set(gc.YEAR, randBetween(1900,2020));
            user.setDate (new Date(gc.YEAR,1,1));
            users.add (user);
        }
    }

    public static void initData(EntityManager em){
        initProducts(em);
    }

    public static void initArticlesData(){
        int i = 0;
        for (Product product : products) {
            product.setName ("Product_" + i++);
            product.setPrice ((double) (Math.random ()*1000));
        }
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    private static void initProducts(EntityManager em) {
        em.getTransaction().begin();

        System.out.println("Products initialized");
        for (Product product : products) {
            em.merge(product);
        }
        em.getTransaction().commit();
    }


    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static void setProducts(ArrayList<Product> products) {
        InitData.products = products;
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}
