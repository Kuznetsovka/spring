package org.example.geekbrains.lesson5.domain;

import javax.persistence.*;

@Entity
//@NamedQueries ({
//        @NamedQuery(name = Product.PRODUCT_FIND_MAX_PRICE, query = "SELECT p.name_fld FROM Products p ORDER BY p.price_fld DESC LIMIT 1"),
//        @NamedQuery(name = Product.PRODUCT_FIND_MIN_PRICE, query = "SELECT p.name_fld FROM Products p ORDER BY p.price_fld ASC LIMIT 1")
//})
        @Table(name = "products")
public class Product {
//    public static final String PRODUCT_FIND_MAX_PRICE = "Product.findMaxPrice";
//    public static final String PRODUCT_FIND_MIN_PRICE = "Product.findMinPrice";
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name_fld")
    private String name;


    @Column(name = "price_fld")
    private Float price;

    @Transient
    private String link;


    public Float getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
