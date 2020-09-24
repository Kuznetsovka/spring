package org.example.geekbrains.lesson6.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
        @Table(name = "products")
public class Product {
    @Id
    @Column(name = "id_product")
    @SequenceGenerator(name = "seqProduct", sequenceName = "seqProduct", allocationSize = 100)
    @GeneratedValue(generator = "seqProduct")
    private Long id;

    @Column(name = "name_fld")
    private String name;

    @Column(name = "price_fld")
    private Double price;

    @ManyToMany
    @JoinTable(
            name = "products_orders",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders;

    public Product() {
    }

    public Double getPrice() {
        return price;
    }
    @Autowired
    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }
    @Autowired
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }
    @Autowired
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    @Autowired
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
