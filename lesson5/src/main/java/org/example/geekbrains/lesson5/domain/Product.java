package org.example.geekbrains.lesson5.domain;

import javax.persistence.*;

@Entity
@NamedQueries ({
        @NamedQuery(name = Product.PRODUCT_FIND_MAX_PRICE, query = "SELECT p.name FROM Product p ORDER BY p.price DESC"),
        @NamedQuery(name = Product.PRODUCT_FIND_MIN_PRICE, query = "SELECT p.name FROM Product p ORDER BY p.price ASC")
})
        @Table(name = "products")
public class Product {
    public static final String PRODUCT_FIND_MAX_PRICE = "Product.findMaxPrice";
    public static final String PRODUCT_FIND_MIN_PRICE = "Product.findMinPrice";
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name_fld")
    private String name;


    @Column(name = "price_fld")
    private Float price;


    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
