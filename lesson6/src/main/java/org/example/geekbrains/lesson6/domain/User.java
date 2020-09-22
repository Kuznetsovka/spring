package org.example.geekbrains.lesson6.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id_user")
    @GeneratedValue
    private Long id;
    @Column(name = "name_fld")
    private String name = "Unknown";
    @Column(name = "email_fld")
    private String email;
    @Column(name = "age_fld")
    private int age;
    @Column(name = "date_fld")
    private Date date = new Date(100,1,1);

    @OneToMany()
    @JoinColumn(name = "order_id")
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getIdUser() {
        return id;
    }

    public void setIdUser(Long idUser) {
        this.id = id;
    }

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String email, int age, Date date) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
