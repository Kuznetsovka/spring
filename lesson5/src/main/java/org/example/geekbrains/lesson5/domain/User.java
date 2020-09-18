package org.example.geekbrains.lesson5.domain;

import java.util.Date;

public class User {

    private String name = "Unknown";
    private String email;
    private int age;
    private Date date = new Date(100,1,1);


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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
