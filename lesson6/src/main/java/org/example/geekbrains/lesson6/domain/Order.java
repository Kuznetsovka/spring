package org.example.geekbrains.lesson6.domain;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id_order")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrder;

    @Column(name = "user_id")
    private int userID;

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    @Column(name = "product_id")
  //  @OneToMany
    private int productID;

    public Order() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + idOrder +
                ", userID=" + userID +
                ", productID=" + productID +
                '}';
    }
}
