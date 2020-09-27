package org.example.geekbrains.lesson7.bootstrap;

import org.example.geekbrains.lesson7.dao.OrderDao;
import org.example.geekbrains.lesson7.dao.ProductDao;
import org.example.geekbrains.lesson7.dao.UserDao;
import org.example.geekbrains.lesson7.domain.Product;
import org.example.geekbrains.lesson7.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Component
public class InitData implements CommandLineRunner {

    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final UserDao userDao;

    public InitData(OrderDao orderDao, ProductDao productDao,UserDao userDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    @Override
    public void run(String... args) {
        initData();
    }

    private static long ind = 99;

    private static ArrayList<Product> products = new ArrayList<> ();
    private static ArrayList<User> users = new ArrayList<> ();

    public static final double COUNT_PRODUCTS = 2;
    public static final double COUNT_USERS = 2;

    @Transactional
    public void initData(){
        int i = 0;
        for (int j = 0; j < COUNT_PRODUCTS; j++) {
            Product product = new Product ();
            product.setName ("Product_" + i++);
            product.setPrice ( Math.random ()*1000);
            products.add (product);
            productDao.save(product);
        }
        i = 0;
        for (int j = 0; j < COUNT_USERS; j++) {
            User user = new User ();
            user.setName ("User_" + i++);
            user.setEmail (user.getName () + "@mail.ru");
            user.setAge ((int) (Math.random ()*100));
            user.setDate (new Date(randBetween(0,2020),1,1));
            users.add (user);
            userDao.save(user);
        }
//        initProducts(em);
//        initUsers(em);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static void setProducts(ArrayList<Product> products) {
        InitData.products = products;
    }

    public static void setUsers(ArrayList<User> users) {
        InitData.users = users;
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    //    private static void initUsers(EntityManager em) {
//        em.getTransaction().begin();
//
//        System.out.println("Users initialized");
//        for (User user : users) {
//            em.merge(user);
//        }
//        em.getTransaction().commit();
//    }



//
//    private static void initProducts(EntityManager em) {
//        em.getTransaction().begin();
//
//        System.out.println("Products initialized");
//        for (Product product : products) {
//            em.merge(product);
//        }
//        em.getTransaction().commit();
//    }

}
