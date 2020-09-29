package org.example.geekbrains.lesson7.dao;

import org.example.geekbrains.lesson7.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    List<User> findAllByName(String name);
    List<User> findAll();
    List<User> findAllByIdBetween(Long startId, Long endId);
    List<User> findAllByNameLike(String name);
    List<User> findByName(String name, Pageable page);
    User findFirstByName(String name);

    @Override
    void deleteById(Long aLong);
}
