package org.example.geekbrains.lesson6.repository;

import org.example.geekbrains.lesson6.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJpaDAO extends JpaRepository<User, Long> {
    List<User> findAllByName(String name);
    List<User> findAllByIdUserBetween(Long startId, Long endId);
    List<User> findAllByNameLike(String name);
    List<User> findByName(String name, Pageable page);

    @Override
    void deleteById(Long aLong);
}
