package org.example.geekbrains.lesson6.domain;

import javax.persistence.*;

@Entity
        @Table(name = "products")
public class Product {
    @Id
    @Column(name = "id_product")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduct;

    @Column(name = "name_fld")
    private String name;

    @Column(name = "price_fld")
    private Double price;

    @Column(name = "order_id")
   // @OneToOne
    private Double orderID;


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
        return idProduct;
    }

    public void setId(Long id) {
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + idProduct +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", orderID=" + orderID +
                '}';
    }
}
