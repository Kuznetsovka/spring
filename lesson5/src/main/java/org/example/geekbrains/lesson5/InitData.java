package org.example.geekbrains.lesson5;

import org.example.geekbrains.lesson5.domain.Product;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class InitData {

    private static long ind = 99;

    private static ArrayList<Product> products = new ArrayList<Product> ();

    public static final double COUNT_PRODUCTS = 20;

    static {
            int i = 0;
            for (int j = 0; j < COUNT_PRODUCTS; j++) {
                Product product = new Product ();
                product.setName ("Product_" + i++);
                product.setPrice ((float) (Math.random ()*1000));
                product.setLink ( "http://localhost:8090/app/products/delete?id="+ i);
                products.add (product);
            }
    }

    public static void initData(EntityManager em){
        initProducts(em);
    }

    public static void initArticlesData(){
        int i = 0;
        for (Product product : products) {
            product.setName ("Product_" + i++);
            product.setPrice ((float) (Math.random ()*1000));
        }
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

}
