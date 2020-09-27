package org.example.geekbrains.lesson7.domain;

import javax.persistence.*;

@Entity
        @Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "seqProduct", sequenceName = "seqProduct", allocationSize = 100)
    @GeneratedValue(generator = "seqProduct")
    private Long id;

    @Column(name = "name_fld")
    private String name;

    @Column(name = "price_fld")
    private Double price;

    public Product() {
    }

    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
