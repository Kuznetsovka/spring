package org.example.geekbrains.lesson6.repository;

import org.example.geekbrains.lesson6.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class UserJpaDAO extends JpaRepository<User, Long> {
    List<User> findAllById
    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty ();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
