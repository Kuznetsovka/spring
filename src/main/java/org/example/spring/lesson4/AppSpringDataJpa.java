package org.example.spring.lesson4;

import org.example.spring.lesson4.config.SpringDataConfig;
import org.example.spring.lesson4.domain.Product;
import org.example.spring.lesson4.service.ProductServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class AppSpringDataJpa {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringDataConfig.class);

        ProductServiceImpl productService = context.getBean(ProductServiceImpl.class);

        ArrayList<Product> products = InitData.getProducts ();
        for (Product product : products) {
            System.out.println(product);
            productService.saveAndSet(product);
        }

        System.out.println("Продукт с 3-м ID " + productService.findNameById(3L));

    }
}
