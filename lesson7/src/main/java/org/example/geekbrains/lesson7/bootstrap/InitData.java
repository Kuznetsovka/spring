package org.example.geekbrains.lesson7.bootstrap;

import org.example.geekbrains.lesson7.dao.OrderDao;
import org.example.geekbrains.lesson7.dao.ProductDao;
import org.example.geekbrains.lesson7.dao.UserDao;
import org.example.geekbrains.lesson7.domain.Product;
import org.example.geekbrains.lesson7.domain.Role;
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

    private static ArrayList<Product> products = new ArrayList<> ();
    private static ArrayList<User> users = new ArrayList<> ();

    public static final double COUNT_PRODUCTS = 2;
    public static final double COUNT_USERS = 4;
    //user1 user
    //admin2 admin
    //super3 super
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
        i = 1;
        for (int j = 1; j < COUNT_USERS; j++) {
            User user = new User ();
            if (j%2==0) {
                user.setName ("admin" + i++);
                user.setPassword("admin");
                user.setRole(Role.ADMIN);
            } else if (j!=3) {
                user.setName ("user" + i++);
                user.setPassword("user");
                user.setRole(Role.MANAGER);
            } else {
                user.setName ("super" + i++);
                user.setPassword("super");
                user.setRole(Role.SUPER_ADMIN);
            }
            user.setEmail (user.getName () + "@mail.ru");
            user.setAge ((int) (Math.random ()*100));
            user.setDate (new Date(randBetween(0,20),1,1));
            users.add (user);
            userDao.save(user);
        }
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
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}
