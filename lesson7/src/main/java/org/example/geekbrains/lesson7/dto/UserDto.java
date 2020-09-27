package org.example.geekbrains.lesson7.dto;

import java.util.Date;
import java.util.List;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private int age;
    private Date date;
    private List<OrderDto> listOrders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<OrderDto> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<OrderDto> listOrders) {
        this.listOrders = listOrders;
    }
}
