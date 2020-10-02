package org.example.geekbrains.lesson7.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "seqOrder", sequenceName = "seqOrder", allocationSize = 100)
    @GeneratedValue(generator = "seqOrder")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Long getId() {
        return id;
    }
    @Autowired
    public void setId(Long id) {
        this.id = id;
    }
    @Autowired
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getIdOrder() {
        return id;
    }
    @Autowired
    public void setIdOrder(Long idOrder) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<Product> getProducts() {
        return products;
    }


    public Order() {
    }

    public Order(Long id, User user, List<Product> products) {
        this.id = id;
        this.user = user;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }
}
