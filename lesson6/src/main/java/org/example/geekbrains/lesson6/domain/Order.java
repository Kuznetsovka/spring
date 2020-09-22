package org.example.geekbrains.lesson6.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id_order")
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }
}
